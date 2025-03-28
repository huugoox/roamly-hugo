package com.example.roamly.data.repository

import android.util.Log
import com.example.roamly.data.local.dao.ItineraryDao
import com.example.roamly.data.local.mapper.toDomain
import com.example.roamly.data.local.mapper.toEntity
import com.example.roamly.domain.models.ItineraryItem
import com.example.roamly.domain.repository.ItineraryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItineraryRepositoryImpl @Inject constructor(
    private val itineraryDao: ItineraryDao
) : ItineraryRepository {

    private val TAG = "ItineraryRepository"  // Variable TAG para los logs

    override suspend fun addItineraryItem(item: ItineraryItem): Boolean {
        Log.d(TAG, "Añadiendo itinerario para el viaje con ID: ${item.tripId}")
        val id = itineraryDao.addItineraryItem(item.toEntity())
        return id > 0
    }

    override suspend fun getItineraryItems(tripId: Int): List<ItineraryItem> {
        Log.d(TAG, "Obteniendo itinerarios para el viaje con ID: $tripId")
        return itineraryDao.getItineraryForTrip(tripId).map { it.toDomain() }
    }

    override suspend fun getItineraryItemById(id: Int): ItineraryItem? {
        Log.d(TAG, "Buscando itinerario con ID: $id")
        return itineraryDao.getItineraryForTrip(id).find { it.id == id }?.toDomain()
    }

    override suspend fun updateItineraryItem(item: ItineraryItem): Boolean {
        Log.d(TAG, "Actualizando itinerario con ID: ${item.id}")
        return itineraryDao.updateItineraryItem(item.toEntity()) > 0
    }

    override suspend fun deleteItineraryItem(id: Int): Boolean {
        Log.d(TAG, "Eliminando itinerario con ID: $id")
        itineraryDao.deleteItineraryItem(id)
        return true
    }
}
