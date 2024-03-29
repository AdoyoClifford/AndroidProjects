package com.adoyo.restaurant.data.remote

import com.adoyo.restaurant.domain.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<RemoteRestaurant>

    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurant(@Query("equalTo")id: Int): Map<String, Restaurant>
}