package com.adoyo.restaurant.domain

import com.adoyo.restaurant.data.RestaurantsRepository

class ToggleRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantUseCase = GetSortedRestaurantUseCase()

    suspend operator fun invoke(id: Int, oldValue: Boolean): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavouriteRestaurant(id, newFav)
        return getSortedRestaurantUseCase()
    }
}