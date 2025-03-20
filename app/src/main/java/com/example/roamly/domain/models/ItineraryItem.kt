package com.example.roamly.domain.models

import java.util.Date

data class ItineraryItem(
    val id: String,
    val tripId: String,
    val description: String,
    val date: Date,
    val location: String,
    val timestamp: Date = Date()
)
