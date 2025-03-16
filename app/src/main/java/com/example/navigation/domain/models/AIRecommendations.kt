package com.example.navigation.domain.models

data class AIRecommendations(
    val id: Int = 0,
    val tripId: String,
    val suggestedActivity: String,
    val rating: Double
)
