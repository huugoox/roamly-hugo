package com.example.roamly.domain.repository
import com.example.roamly.domain.models.Map

interface MapRepository {
    fun getCurrentLocation(): Map?
    fun getNearbyPlaces(latitude: Double, longitude: Double): List<Map>
}