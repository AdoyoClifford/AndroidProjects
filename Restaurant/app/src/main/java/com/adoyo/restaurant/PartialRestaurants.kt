package com.adoyo.restaurant

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PartialRestaurants(
    @ColumnInfo(name = "r_id")
    val id: Int,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean = false
)