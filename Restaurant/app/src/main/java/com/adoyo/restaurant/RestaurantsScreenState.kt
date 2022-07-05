package com.adoyo.restaurant

data class RestaurantsScreenState(
    val restaurant: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)
