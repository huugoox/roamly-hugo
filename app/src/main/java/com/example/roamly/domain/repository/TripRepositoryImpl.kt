package com.example.roamly.domain.repository

import android.util.Log
import com.example.roamly.domain.models.Trip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor() : TripRepository {

    private val trips = mutableListOf<Trip>()
    private val TAG = "TripRepositoryImpl"  // Etiqueta para los logs

    override fun addTrip(trip: Trip): Boolean {
        Log.d(TAG, "Intentando agregar un viaje con ID: ${trip.id}")
        return if (trips.any { it.id == trip.id }) {
            Log.w(TAG, "El viaje con ID ${trip.id} ya existe, no se puede agregar.")
            false
        } else {
            trips.add(trip)
            Log.i(TAG, "Viaje con ID ${trip.id} agregado exitosamente.")
            true
        }
    }

    override fun getTripById(id: String): Trip? {
        Log.d(TAG, "Buscando viaje con ID: $id")
        val trip = trips.find { it.id == id }
        if (trip != null) {
            Log.i(TAG, "Viaje encontrado: $trip")
        } else {
            Log.w(TAG, "No se encontró el viaje con ID: $id")
        }
        return trip
    }

    override fun getAllTrips(): List<Trip> {
        Log.d(TAG, "Obteniendo todos los viajes, cantidad total: ${trips.size}")
        return trips.sortedByDescending { it.startDate }
    }

    override fun updateTrip(trip: Trip): Boolean {
        Log.d(TAG, "Intentando actualizar viaje con ID: ${trip.id}")
        val index = trips.indexOfFirst { it.id == trip.id }
        return if (index != -1) {
            trips[index] = trip
            Log.i(TAG, "Viaje con ID ${trip.id} actualizado exitosamente.")
            true
        } else {
            Log.w(TAG, "No se encontró el viaje con ID ${trip.id} para actualizar.")
            false
        }
    }

    override fun deleteTrip(id: String): Boolean {
        Log.d(TAG, "Intentando eliminar viaje con ID: $id")
        val result = trips.removeIf { it.id == id }
        if (result) {
            Log.i(TAG, "Viaje con ID $id eliminado exitosamente.")
        } else {
            Log.w(TAG, "No se encontró el viaje con ID $id para eliminar.")
        }
        return result
    }
}
