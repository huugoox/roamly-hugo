package com.example.navigation.models

data class AIRecommendations(
    val recommendationId: String,
    val tripId: String,
    val suggestedActivity: String,
    val rating: Double
) {
    // @TODO: Implement function to generate recommendations
    fun generateRecommendations() {}
}
