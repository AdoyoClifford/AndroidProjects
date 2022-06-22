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

class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel(){
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
                   state.value = restaurants.restoreSelection()
       }
    }
    private suspend fun getAllRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                val restaurants = restInterface.getRestaurants()
                restaurantsDao.addAll(restaurants)
                return@withContext restaurants
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        return@withContext restaurantsDao.getAll()
                    }
                    else -> throw e
                }
            }
        }
    }

    fun toggleFavorite(id: Int){
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES)
            .orEmpty().toMutableList()
        if (item.isFavorite) savedToggled.add(item.id)
        else savedToggled.remove(item.id)
        stateHandle[FAVORITES] = savedToggled
    }

    private fun List<Restaurant>.restoreSelection(): List<Restaurant> {
            stateHandle.get<List<Int>?>(FAVORITES)?.let {
                    selectedIds ->
                val restaurantsMap = this.associateBy { it.id }.toMutableMap()
                selectedIds.forEach { id ->
                    val restaurant = restaurantsMap[id] ?: return@forEach
                    restaurantsMap[id] = restaurant.copy(isFavorite = true)
                }
                return restaurantsMap.values.toList()
            }
            return this
    }
    companion object {
        const val FAVORITES = "favorites"
    }
}