package com.example.navigation.domain.repository

import com.example.navigation.domain.models.AIRecommendations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AIRecommendationsRepositoryImpl @Inject constructor(): AIRecommendationsRepository {

    private val recommendations = mutableListOf<AIRecommendations>()

    override fun getRecommendations(): List<AIRecommendations> {
        return recommendations
    }

    override fun generateRecommendation(tripId: String, activity: String, rating: Double) {
        val newRecommendation = AIRecommendations(
            id = recommendations.size + 1,
            tripId = tripId,
            suggestedActivity = activity,
            rating = rating
        )
        recommendations.add(newRecommendation)
    }

    override fun deleteRecommendation(recommendationId: Int) {
        recommendations.removeAll{ it.id == recommendationId }
    }

    override fun updateRecommendation(recommendation: AIRecommendations) {
        val index = recommendations.indexOfFirst { it.id == recommendation.id }
        if (index != -1) {
            recommendations[index] = recommendation
        }
    }

    override fun rateRecommendation(recommendationId: Int, rating: Double) {
        val index = recommendations.indexOfFirst { it.id == recommendationId }
        if (index != -1) {
            recommendations[index] = recommendations[index].copy(rating = rating)
        }
    }
}