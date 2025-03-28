package com.example.roamly.domain.repository

import com.example.roamly.domain.models.ItineraryItem

interface ItineraryRepository {

    suspend fun addItineraryItem(item: ItineraryItem): Boolean
    suspend fun getItineraryItems(tripId: Int): List<ItineraryItem>
    suspend fun getItineraryItemById(id: Int): ItineraryItem?
    suspend fun updateItineraryItem(item: ItineraryItem): Boolean
    suspend fun deleteItineraryItem(id: Int): Boolean

}