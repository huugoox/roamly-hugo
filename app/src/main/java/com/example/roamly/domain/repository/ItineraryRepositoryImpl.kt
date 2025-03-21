package com.example.roamly.domain.repository

import android.util.Log
import com.example.roamly.domain.models.ItineraryItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItineraryRepositoryImpl @Inject constructor() : ItineraryRepository {

    private val itineraryItems = mutableListOf<ItineraryItem>()
    private val TAG = "ItineraryRepository"  // Variable TAG para los logs

    override fun addItineraryItem(item: ItineraryItem): Boolean {
        Log.d(TAG, "Intentando añadir itinerario con ID: ${item.id}")
        return if (itineraryItems.any { it.id == item.id }) {
            Log.d(TAG, "El itinerario con ID: ${item.id} ya existe.")
            false
        } else {
            itineraryItems.add(item)
            Log.d(TAG, "Itinerario con ID: ${item.id} añadido exitosamente.")
            true
        }
    }

    override fun getItineraryItems(tripId: String): List<ItineraryItem> {
        Log.d(TAG, "Obteniendo itinerarios para el viaje con ID: $tripId")
        val items = itineraryItems.filter { it.tripId == tripId }.sortedBy { it.date }
        Log.d(TAG, "Total de itinerarios encontrados: ${items.size}")
        return items
    }

    override fun getItineraryItemById(id: String): ItineraryItem? {
        Log.d(TAG, "Buscando itinerario con ID: $id")
        val item = itineraryItems.find { it.id == id }
        if (item != null) {
            Log.d(TAG, "Itinerario con ID: $id encontrado.")
        } else {
            Log.d(TAG, "Itinerario con ID: $id no encontrado.")
        }
        return item
    }

    override fun updateItineraryItem(item: ItineraryItem): Boolean {
        Log.d(TAG, "Intentando actualizar itinerario con ID: ${item.id}")
        val index = itineraryItems.indexOfFirst { it.id == item.id }
        return if (index != -1) {
            itineraryItems[index] = item
            Log.d(TAG, "Itinerario con ID: ${item.id} actualizado exitosamente.")
            true
        } else {
            Log.d(TAG, "Itinerario con ID: ${item.id} no encontrado para actualizar.")
            false
        }
    }

    override fun deleteItineraryItem(id: String): Boolean {
        Log.d(TAG, "Intentando eliminar itinerario con ID: $id")
        val result = itineraryItems.removeIf { it.id == id }
        if (result) {
            Log.d(TAG, "Itinerario con ID: $id eliminado exitosamente.")
        } else {
            Log.d(TAG, "Itinerario con ID: $id no encontrado para eliminar.")
        }
        return result
    }
}
