package com.example.roamly.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AIRecommendations(
    val id: Int = 0,
    val tripId: Int,
    val suggestedActivity: String,
    val rating: Double
)
