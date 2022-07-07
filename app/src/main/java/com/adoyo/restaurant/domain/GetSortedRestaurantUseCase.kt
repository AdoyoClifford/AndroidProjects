package com.adoyo.restaurant.domain

import com.adoyo.restaurant.data.RestaurantsRepository

class GetSortedRestaurantUseCase {
    private val repository = RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant>{
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}