package com.adoyo.cards

import androidx.annotation.DrawableRes
import com.example.cards.R

data class Items(@DrawableRes val image: Int, var text: String)



fun createDataList(): List<Items>{
    val list = mutableListOf<Items>()
    list.add(Items(R.drawable.reading, text = "Students"))
    list.add(Items(R.drawable.graduated, text = "Academic"))
    list.add(Items(R.drawable.attendance, text = "Attendance"))
    list.add(Items(R.drawable.slumber, text = "Leave"))
    list.add(Items(R.drawable.content, text = "Contents"))
    list.add(Items(R.drawable.message, text = "Notice"))
    list.add(Items(R.drawable.book, text = "Library"))
    list.add(Items(R.drawable.homework, text = "Homework"))
    list.add(Items(R.drawable.info, text = "About"))
    list.add(Items(R.drawable.button, text = "Settings"))

    return list
}
