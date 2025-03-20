package com.example.roamly.domain.repository

import com.example.roamly.domain.models.Trip

interface TripRepository {
    fun addTrip(trip: Trip): Boolean
    fun getTripById(id: String): Trip?
    fun getAllTrips(): List<Trip>
    fun updateTrip(trip: Trip): Boolean
    fun deleteTrip(id: String): Boolean
}