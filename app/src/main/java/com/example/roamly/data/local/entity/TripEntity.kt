package com.example.roamly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val destination: String,
    val startDate: Long,
    val endDate: Long,
    val budget: Double,
    val notes: String,
    val isFavorite: Boolean,
    val coverImageUrl: String
)