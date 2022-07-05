package com.adoyo.restaurant

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class RestaurantsViewModel(): ViewModel(){
    private val repository = RestaurantsRepository()
    private val _state = mutableStateOf(RestaurantsScreenState(
        restaurant = listOf(),
        isLoading = true
    ))

    //preventing composable from mutating viewModels state
    val state: State<RestaurantsScreenState>
        get() = _state

    //To adda retry button the error should be set to false so that when the button is
    //pressed the UI wont remain in error state indefinitely
    private val errorHandler = CoroutineExceptionHandler{ _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    init {
        getRestaurants()
    }
   private fun getRestaurants(){
       viewModelScope.launch (errorHandler){
               val restaurants = repository.getAllRestaurants()
                   _state.value = _state.value.copy(restaurant = restaurants,isLoading = false)
       }
    }

    //passing restaurants from local database
    fun toggleFavorite(id: Int, oldValue: Boolean){
        viewModelScope.launch (errorHandler){
            val updateRestaurants = repository.toggleFavouriteRestaurant(id, oldValue)
            _state.value = _state.value.copy(restaurant = updateRestaurants)
        }
    }
}

