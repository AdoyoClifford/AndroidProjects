package com.adoyo.restaurant

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsViewModel(): ViewModel(){
    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())
    val state = mutableStateOf(emptyList<Restaurant>())
    private val restInterface: RestaurantApiService
    private val errorHandler = CoroutineExceptionHandler{ _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-1c60d-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantApiService::class.java)
        getRestaurants()
    }
   private fun getRestaurants(){
       viewModelScope.launch (errorHandler){
               val restaurants = getAllRestaurants()
                   state.value = getAllRestaurants()
       }
    }
    private suspend fun getAllRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (restaurantsDao.getAll().isEmpty()){
                            throw Exception("Something went wrong"+ "We have no data.")
                        }
                    }
                    else -> throw e
                }
            }
            return@withContext restaurantsDao.getAll()
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
    //Make toggleFavorite restaurants return from our local database
    private suspend fun toggleFavouriteRestaurant(id: Int,oldValue: Boolean) =
        withContext(Dispatchers.IO){
            restaurantsDao.update(PartialRestaurant(id = id, isFavourite = !oldValue)
            )
        restaurantsDao.getAll()
        }

    //passing restaurants from local database
    fun toggleFavorite(id: Int, oldValue: Boolean){
        viewModelScope.launch (errorHandler){
            val updateRestaurants = toggleFavouriteRestaurant(id,oldValue)
            state.value = updateRestaurants
        }
    }
}

