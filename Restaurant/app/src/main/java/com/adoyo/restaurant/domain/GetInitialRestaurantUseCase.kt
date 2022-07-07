package com.adoyo.restaurant.domain

import com.adoyo.restaurant.data.RestaurantsRepository

class GetInitialRestaurantUseCase{
    private val repository = RestaurantsRepository()
    private val getSortedRestaurantUseCase = GetSortedRestaurantUseCase()

    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantUseCase()
    }
}