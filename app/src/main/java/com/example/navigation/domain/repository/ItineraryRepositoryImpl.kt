package com.example.navigation.domain.repository

import com.example.navigation.domain.models.ItineraryItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItineraryRepositoryImpl @Inject constructor() : ItineraryRepository {

    private val itineraryItems = mutableListOf<ItineraryItem>()

    override fun addItineraryItem(item: ItineraryItem): Boolean {
        return if (itineraryItems.any() { it.id == item.id }) {
            false
        } else {
            itineraryItems.add(item)
            true
        }
    }

    override fun getItineraryItems(tripId: String): List<ItineraryItem> {
        return itineraryItems.filter { it.tripId == tripId }.sortedBy { it.date }
    }

    override fun getItineraryItemById(id: String): ItineraryItem? {
        return itineraryItems.find { it.id == id }
    }

    override fun updateItineraryItem(item: ItineraryItem): Boolean {
        val index = itineraryItems.indexOfFirst { it.id == item.id }
        return if (index != -1) {
            itineraryItems[index] = item
            true
        } else {
            false
        }
    }

    override fun deleteItineraryItem(id: String): Boolean {
        return itineraryItems.removeIf { it.id == id }
    }

}