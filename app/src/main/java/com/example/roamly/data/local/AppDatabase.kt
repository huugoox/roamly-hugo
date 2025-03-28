package com.example.roamly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roamly.data.local.dao.ItineraryDao
import com.example.roamly.data.local.dao.TripDao
import com.example.roamly.data.local.entity.ItineraryItemEntity
import com.example.roamly.data.local.entity.TripEntity

@Database(
    entities = [TripEntity::class, ItineraryItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun itineraryDao(): ItineraryDao
}