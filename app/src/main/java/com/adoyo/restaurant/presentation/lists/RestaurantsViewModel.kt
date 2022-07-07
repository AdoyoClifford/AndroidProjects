package com.adoyo.restaurant.presentation.lists

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adoyo.restaurant.data.RestaurantsRepository
import com.adoyo.restaurant.domain.GetInitialRestaurantUseCase
import com.adoyo.restaurant.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.*

class RestaurantsViewModel(): ViewModel(){
    private val toggleRestaurantUseCase = ToggleRestaurantUseCase()
    private val getRestaurantUseCase = GetInitialRestaurantUseCase()
    private val repository = RestaurantsRepository()
    private val _state = mutableStateOf(RestaurantsScreenState(
        restaurant = listOf(),
        isLoading = true
    ))

    //preventing composable from mutating viewModels state
    val state: State<RestaurantsScreenState>
        get() = _state

    //To add retry button the error should be set to false so that when the button is
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
               val restaurants = getRestaurantUseCase()
                   _state.value = _state.value.copy(restaurant = restaurants,isLoading = false)
       }
    }

    //passing restaurants from local database
    fun toggleFavorite(id: Int, oldValue: Boolean){
        viewModelScope.launch (errorHandler){
            val updateRestaurants = toggleRestaurantUseCase(id, oldValue)
            _state.value = _state.value.copy(restaurant = updateRestaurants)
        }
    }
}

