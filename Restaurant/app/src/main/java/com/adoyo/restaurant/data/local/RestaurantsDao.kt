package com.adoyo.restaurant.data.local

import androidx.room.*
import com.adoyo.restaurant.data.local.LocalRestaurant
import com.adoyo.restaurant.data.local.PartialLocalRestaurant

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurant")
    fun getAll(): List<LocalRestaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAll(restaurants: List<LocalRestaurant>)

    @Update(entity = LocalRestaurant::class)
     fun update(partialRestaurant: PartialLocalRestaurant)

    @Update(entity = LocalRestaurant::class)
     fun updateAll(partialRestaurants: List<PartialLocalRestaurant>)

    @Query("SELECT * FROM restaurant WHERE is_favorite = 1")
     fun getAllFavorited(): List<LocalRestaurant>

}
