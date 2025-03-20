package com.example.roamly.domain.repository

import com.example.roamly.domain.models.Trip
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class TripRepositoryImplTest {

    private lateinit var tripRepository: TripRepositoryImpl
    private lateinit var sampleTrip: Trip
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    @Before
    fun setup() {
        // Inicializa el repositorio y un objeto Trip de ejemplo con todos los campos requeridos
        tripRepository = TripRepositoryImpl()

        val startDate: Date = dateFormat.parse("2025-03-10")!!
        val endDate: Date = dateFormat.parse("2025-03-20")!!

        sampleTrip = Trip(
            id = "1",
            destination = "Barcelona",
            startDate = startDate,
            endDate = endDate,
            budget = 1500.0,
            notes = "Viaje familiar",
            isFavorite = false,
            coverImageUrl = "https://example.com/cover.jpg"
        )
    }

    @Test
    fun `addTrip should add trip successfully when id is unique`() {
        // Act
        val result = tripRepository.addTrip(sampleTrip)

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque es un nuevo trip
        assertEquals(1, tripRepository.getAllTrips().size)  // Debe haber un trip en la lista
        assertEquals(sampleTrip, tripRepository.getTripById("1"))  // El trip agregado debe coincidir
    }

    @Test
    fun `addTrip should fail when id already exists`() {
        // Arrange
        tripRepository.addTrip(sampleTrip)

        // Act
        val result = tripRepository.addTrip(sampleTrip)  // Intenta agregar el mismo trip otra vez

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el trip ya existe
        assertEquals(1, tripRepository.getAllTrips().size)  // La lista debe seguir teniendo solo un trip
    }

    @Test
    fun `updateTrip should update an existing trip`() {
        // Arrange
        tripRepository.addTrip(sampleTrip)
        val updatedTrip = sampleTrip.copy(destination = "Madrid", budget = 2000.0)

        // Act
        val result = tripRepository.updateTrip(updatedTrip)

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque el trip fue actualizado
        assertEquals("Madrid", tripRepository.getTripById("1")?.destination)  // El destino debe ser "Madrid"
        assertEquals(2000.0, tripRepository.getTripById("1")?.budget)  // El presupuesto debe ser 2000.0
    }

    @Test
    fun `updateTrip should fail when trip does not exist`() {
        // Act
        val result = tripRepository.updateTrip(sampleTrip)

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el trip no existe en la lista
    }

    @Test
    fun `deleteTrip should remove the trip if it exists`() {
        // Arrange
        tripRepository.addTrip(sampleTrip)

        // Act
        val result = tripRepository.deleteTrip("1")

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque el trip fue eliminado
        assertEquals(0, tripRepository.getAllTrips().size)  // La lista debe estar vacía
    }

    @Test
    fun `deleteTrip should fail when trip does not exist`() {
        // Act
        val result = tripRepository.deleteTrip("1")

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el trip no existía
    }
}
