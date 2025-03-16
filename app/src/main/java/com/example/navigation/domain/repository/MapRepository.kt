package com.example.navigation.domain.repository
import com.example.navigation.domain.models.Map

interface MapRepository {
    fun getCurrentLocation(): Map?
    fun getNearbyPlaces(latitude: Double, longitude: Double): List<Map>
}