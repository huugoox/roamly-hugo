package com.example.roamly.domain.models

import java.util.Date

data class ItineraryItem(
    val id: Int,
    val tripId: Int,
    val description: String,
    val date: Date,
    val location: String,
)
