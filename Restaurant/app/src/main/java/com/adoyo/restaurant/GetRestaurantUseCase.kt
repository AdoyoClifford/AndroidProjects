package com.adoyo.restaurant

class GetRestaurantUseCase{
    private val repository = RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant> {
        return repository.getAllRestaurants()
            .sortedBy { it.title }
    }
}