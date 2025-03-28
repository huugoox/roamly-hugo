package com.example.roamly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roamly.data.local.entity.TripEntity

@Dao
interface TripDao {
    @Query("SELECT * FROM trips")
    suspend fun getTrips(): List<TripEntity>

    @Query("SELECT * FROM trips WHERE id = :tripId")
    suspend fun getTripById(tripId: Int): TripEntity?

    @Insert
    suspend fun addTrip(trip: TripEntity): Long

    @Query("DELETE FROM trips WHERE id = :tripId")
    suspend fun deleteTrip(tripId: Int)

    @Update
    suspend fun updateTrip(trip: TripEntity): Int
}