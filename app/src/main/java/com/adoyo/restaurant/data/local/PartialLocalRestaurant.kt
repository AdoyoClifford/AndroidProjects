package com.adoyo.restaurant.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PartialLocalRestaurant(
    @ColumnInfo(name = "r_id")
    val id: Int,
    @ColumnInfo(name = "is_favorite")
    val isFavourite: Boolean = false
)