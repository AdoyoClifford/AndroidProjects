package com.adoyo.restaurant.data

import com.adoyo.restaurant.RestaurantsApplication
import com.adoyo.restaurant.data.local.LocalRestaurant
import com.adoyo.restaurant.data.local.PartialLocalRestaurant
import com.adoyo.restaurant.data.local.RestaurantsDb
import com.adoyo.restaurant.data.remote.RestaurantApiService
import com.adoyo.restaurant.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsRepository {
    private var restInterface: RestaurantApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restaurants-1c60d-default-rtdb.firebaseio.com/")
        .build()
        .create(RestaurantApiService::class.java)

    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())

    //Make toggleFavorite restaurants return from our local database
     suspend fun toggleFavouriteRestaurant(id: Int, value: Boolean) =
        withContext(Dispatchers.IO){
            restaurantsDao.update(PartialLocalRestaurant(id = id, isFavourite = value)
            )
        }
    suspend fun getRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            return@withContext restaurantsDao.getAll()
                .map { Restaurant(it.id,it.title,it.description,it.isFavorite) }
        }
    }

     suspend fun loadRestaurants(){
        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException
                    -> {
                        if (restaurantsDao.getAll().isEmpty()){
                            throw Exception("Something went wrong"+ "We have no data.")
                        }
                    }
                    else -> throw e
                }
            }
        }
    }

    //Get restaurants from remote server and then cache them locally
    private suspend fun refreshCache(){
        val remoteRestaurants = restInterface.getRestaurants()
        val favouriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants.map{
            LocalRestaurant(it.id,it.title,it.description,false)
        })
        //partially update favorite field
        restaurantsDao.updateAll(favouriteRestaurants.map{
            PartialLocalRestaurant(it.id, true)
        })
    }

}