package com.example.roamly.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roamly.domain.models.Trip
import com.example.roamly.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    private val _trips = mutableStateListOf<Trip>()
    val trips: List<Trip> get() = _trips

    init {
        loadTrips()
    }

    private fun loadTrips() {
        Log.d("TripViewModel", "Cargando todos los viajes...")
        _trips.clear()
        _trips.addAll(repository.getAllTrips())
        Log.d("TripViewModel", "Viajes cargados: ${_trips.size} viajes encontrados.")
    }

    fun addTrip(destination: String, startDate: Date, endDate: Date, budget: Double, notes: String, isFavorite: Boolean, coverImageUrl: String?) {
        if (destination.isNotEmpty()) {
            Log.d("TripViewModel", "Intentando agregar un nuevo viaje a $destination.")
            val newTrip = Trip(
                id = UUID.randomUUID().toString(),
                destination = destination,
                startDate = startDate,
                endDate = endDate,
                budget = budget,
                notes = notes,
                isFavorite = isFavorite,
                coverImageUrl = coverImageUrl
            )

            viewModelScope.launch {
                if (repository.addTrip(newTrip)) {
                    _trips.add(newTrip)
                    Log.d("TripViewModel", "Viaje a $destination agregado exitosamente.")
                } else {
                    Log.d("TripViewModel", "No se pudo agregar el viaje a $destination.")
                }
            }
        }
    }

    fun deleteTrip(tripId: String) {
        Log.d("TripViewModel", "Intentando eliminar el viaje con ID: $tripId")
        viewModelScope.launch {
            if (repository.deleteTrip(tripId)) {
                _trips.removeAll { it.id == tripId }
                Log.d("TripViewModel", "Viaje con ID: $tripId eliminado exitosamente.")
            } else {
                Log.d("TripViewModel", "No se pudo eliminar el viaje con ID: $tripId.")
            }
        }
    }

    fun updateTrip(updatedTrip: Trip) {
        Log.d("TripViewModel", "Intentando actualizar el viaje con ID: ${updatedTrip.id}")
        viewModelScope.launch {
            if (repository.updateTrip(updatedTrip)) {
                val index = _trips.indexOfFirst { it.id == updatedTrip.id }
                if (index != -1) {
                    _trips[index] = updatedTrip
                    Log.d("TripViewModel", "Viaje con ID: ${updatedTrip.id} actualizado correctamente.")
                }
            } else {
                Log.d("TripViewModel", "No se pudo actualizar el viaje con ID: ${updatedTrip.id}.")
            }
        }
    }

}
