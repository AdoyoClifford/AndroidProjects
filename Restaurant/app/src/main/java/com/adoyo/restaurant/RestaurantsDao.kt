package com.adoyo.restaurant

import androidx.room.*

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurant")
    suspend fun getAll(): List<Restaurant>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)
    @Update(entity = Restaurant::class)
    suspend fun update(partialUpdate: PartialUpdates)
}