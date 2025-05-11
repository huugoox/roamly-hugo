package com.example.roamly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roamly.data.local.dao.AccessLogDao
import com.example.roamly.data.local.dao.ItineraryDao
import com.example.roamly.data.local.dao.TripDao
import com.example.roamly.data.local.dao.UsersDao
import com.example.roamly.data.local.entity.AccessLogEntity
import com.example.roamly.data.local.entity.ItineraryItemEntity
import com.example.roamly.data.local.entity.TripEntity
import com.example.roamly.data.local.entity.UserEntity

@Database(
    entities = [TripEntity::class, ItineraryItemEntity::class, UserEntity::class, AccessLogEntity::class],
    //entities = [TripEntity::class, ItineraryItemEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun itineraryDao(): ItineraryDao
    abstract fun usersDao(): UsersDao
    abstract fun accessLogDao(): AccessLogDao
}