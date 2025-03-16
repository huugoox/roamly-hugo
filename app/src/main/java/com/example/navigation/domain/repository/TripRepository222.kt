package com.example.navigation.domain.repository

import com.example.navigation.domain.models.Trip
import com.example.navigation.domain.models.ItineraryItem
import com.example.navigation.domain.models.AIRecommendations
import java.util.Date

object TripRepository222 {
    val trips = listOf(
        Trip(
            id = "1",
            destination = "Paris, France",
            startDate = Date(2024, 5, 10),
            endDate = Date(2024, 5, 20),
            budget = 2000.0
        ),
        Trip(
            id = "2",
            destination = "Tokyo, Japan",
            startDate = Date(2024, 7, 5),
            endDate = Date(2024, 7, 15),
            budget = 3000.0
        ),
        Trip(
            id = "3",
            destination = "New York, USA",
            startDate = Date(2024, 9, 1),
            endDate = Date(2024, 9, 10),
            budget = 2500.0
        )
    )

    val itineraryItems = listOf(
        ItineraryItem(
            id = "1",
            description = "Visit Eiffel Tower",
            date = Date(2024, 5, 11),
            location = "Paris",
            timestamp = Date().time
        ),
        ItineraryItem(
            id = "2",
            description = "Shibuya Crossing experience",
            date = Date(2024, 7, 6),
            location = "Tokyo"
        ),
        ItineraryItem(
            id = "3",
            description = "Statue of Liberty Tour",
            date = Date(2024, 9, 2),
            location = "New York"
        )
    )

    val aiRecommendations = listOf(
        AIRecommendations(
            id = 1,
            tripId = "1",
            suggestedActivity = "Try French pastries at a local bakery",
            rating = 4.8
        ),
        AIRecommendations(
            id = 2,
            tripId = "2",
            suggestedActivity = "Visit Akihabara for tech and anime shopping",
            rating = 4.7
        ),
        AIRecommendations(
            id = 3,
            tripId = "3",
            suggestedActivity = "Walk through Central Park",
            rating = 4.9
        )
    )
}
