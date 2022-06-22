package com.adoyo.restaurant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Restaurant::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantsDb: RoomDatabase() {
    abstract val dao: RestaurantsDao

    companion object {

        @Volatile
        private var INSTANCE: RestaurantsDao? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDaoInstance(context: Context): RestaurantsDao{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDataBase(context).dao
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDataBase(context: Context):
                RestaurantsDb =
            Room.databaseBuilder(
                context.applicationContext,
                RestaurantsDb::class.java,"restaurants_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}