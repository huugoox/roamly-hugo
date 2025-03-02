package com.example.navigation.models

import androidx.compose.ui.graphics.painter.Painter
import java.util.Date

data class Trip(
    val id: String,
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val budget: Double
) {
    // @TODO: Implement function to add an itinerary item
    fun addItineraryItem() {}

    // @TODO: Implement function to remove an itinerary item
    fun removeItineraryItem() {}

    //@TODO: Implement function to update an itinerary details
    fun updateTripDetails() {}
}
