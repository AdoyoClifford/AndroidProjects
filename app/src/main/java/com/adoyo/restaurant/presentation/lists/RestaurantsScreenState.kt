package com.adoyo.restaurant.presentation.lists

import com.adoyo.restaurant.domain.Restaurant

data class RestaurantsScreenState(
    val restaurant: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)
