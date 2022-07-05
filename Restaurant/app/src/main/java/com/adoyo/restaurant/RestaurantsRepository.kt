package com.adoyo.restaurant

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
     suspend fun toggleFavouriteRestaurant(id: Int,oldValue: Boolean) =
        withContext(Dispatchers.IO){
            restaurantsDao.update(PartialRestaurant(id = id, isFavourite = !oldValue)
            )
            restaurantsDao.getAll()
        }

     suspend fun getAllRestaurants(): List<Restaurant> {
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
            return@withContext restaurantsDao.getAll()
                .sortedBy { it.title }
        }
    }

    //Get restaurants from remote server and then cache them locally
    private suspend fun refreshCache(){
        val remoteRestaurants = restInterface.getRestaurants()
        val favouriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants)
        //partially update favorite field
        restaurantsDao.updateAll(favouriteRestaurants.map{
            PartialRestaurant(it.id, true)
        })
    }

}