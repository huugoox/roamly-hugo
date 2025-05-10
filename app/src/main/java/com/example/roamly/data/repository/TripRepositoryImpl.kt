package com.example.roamly.data.repository

import android.util.Log
import com.example.roamly.data.local.dao.ItineraryDao
import com.example.roamly.data.local.dao.TripDao
import com.example.roamly.data.local.mapper.toDomain
import com.example.roamly.data.local.mapper.toEntity
import com.example.roamly.domain.models.Trip
import com.example.roamly.domain.repository.TripRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val itineraryDao: ItineraryDao
) : TripRepository {

    private val TAG = "TripRepositoryImpl"  // Etiqueta para los logs

    override suspend fun addTrip(trip: Trip): Boolean {
        Log.d(TAG, "Intentando agregar un viaje con ID: ${trip.id}")
        val tripId = tripDao.addTrip(trip.toEntity())
        return tripId > 0
    }

    suspend override fun getTripById(id: Int): Trip? {
        val tripEntity = tripDao.getTripById(id) ?: return null
        val itineraryItems = itineraryDao.getItineraryForTrip(id).map { it.toDomain() }
        return tripEntity.toDomain(itineraryItems)
    }

    override suspend fun getTripsByUserId(userId: Int): List<Trip> {
        return tripDao.getTripsByUserId(userId).map { it.toDomain() }
    }

    suspend override fun getAllTrips(): List<Trip> {
        Log.d(TAG, "Obteniendo todos los viajes desde la base de datos")
        return tripDao.getTrips().map { it.toDomain() }
    }

    suspend override fun updateTrip(trip: Trip): Boolean {
        Log.d(TAG, "Actualizando viaje con ID: ${trip.id}")
        return tripDao.updateTrip(trip.toEntity()) > 0
    }

    suspend override fun deleteTrip(id: Int): Boolean {
        Log.d(TAG, "Eliminando viaje con ID: $id")
        tripDao.deleteTrip(id)
        return true
    }
}
