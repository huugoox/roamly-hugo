package com.example.roamly.domain.models

import java.time.LocalTime
import java.util.Date

data class ItineraryItem(
    val id: Int,
    val tripId: Int,
    val description: String,
    val date: Date,
    val location: String,
    //val startTime: LocalTime,
    //val endTime: LocalTime,
)
