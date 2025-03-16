package com.example.navigation.domain.repository

import com.example.navigation.domain.models.Trip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor() : TripRepository {

    private val trips = mutableListOf<Trip>()

    override fun addTrip(trip: Trip): Boolean {
        return if(trips.any() { it.id == trip.id }) {
            false
        } else {
            trips.add(trip)
            true
        }
    }

    override fun getTripById(id: String): Trip? {
        return trips.find { it.id == id }
    }

    override fun getAllTrips(): List<Trip> {
        return trips.sortedByDescending { it.startDate }
    }

    override fun updateTrip(trip: Trip): Boolean {
        val index = trips.indexOfFirst { it.id == trip.id }
        return if (index != -1) {
            trips[index] = trip
            true
        } else {
            false
        }
    }

    override fun deleteTrip(id: String): Boolean {
        return trips.removeIf { it.id == id }
    }
}