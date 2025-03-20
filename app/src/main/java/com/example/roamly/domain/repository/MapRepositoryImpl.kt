package com.example.roamly.domain.repository

import javax.inject.Inject
import javax.inject.Singleton
import com.example.roamly.domain.models.Map

@Singleton
class MapRepositoryImpl @Inject constructor() : MapRepository {

    private val currentLocation = Map("Ciudad Actual", 40.4168, -3.7038) // Això és Madrid

    override fun getCurrentLocation(): Map? {
        return currentLocation
    }

    override fun getNearbyPlaces(latitude: Double, longitude: Double): List<Map> {
        return listOf(
            Map("Parque Central", latitude + 0.01, longitude + 0.01),
            Map("Museo Histórico", latitude - 0.02, longitude + 0.015),
            Map("Cafetería Popular", latitude + 0.005, longitude - 0.01)        )
    }
}