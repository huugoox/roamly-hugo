package com.example.roamly.domain.repository

import com.example.roamly.domain.models.ItineraryItem

interface ItineraryRepository {

    fun addItineraryItem(item: ItineraryItem): Boolean
    fun getItineraryItems(tripId: String): List<ItineraryItem>
    fun getItineraryItemById(id: String): ItineraryItem?
    fun updateItineraryItem(item: ItineraryItem): Boolean
    fun deleteItineraryItem(id: String): Boolean

}