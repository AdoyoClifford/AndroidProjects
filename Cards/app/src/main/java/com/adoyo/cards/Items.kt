package com.adoyo.cards

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class Items(var icon: ImageVector, var text: String)



fun createDataList(): List<Items>{
    val list = mutableListOf<Items>()
    list.add(Items(icon = Icons.Default.Home, text = "Students"))
    list.add(Items(icon = Icons.Default.Home, text = "Academic"))
    list.add(Items(icon = Icons.Default.Home, text = "Attendance"))
    list.add(Items(icon = Icons.Default.Home, text = "Leave"))
    list.add(Items(icon = Icons.Default.Home, text = "Contents"))
    list.add(Items(icon = Icons.Default.Home, text = "Notice"))
    list.add(Items(icon = Icons.Default.Home, text = "Library"))
    list.add(Items(icon = Icons.Default.Home, text = "Homework"))
    list.add(Items(icon = Icons.Default.Home, text = "About"))
    list.add(Items(icon = Icons.Default.Home, text = "Settings"))

    return list
}
