package com.example.roamly.domain.repository

import com.example.roamly.data.repository.TripRepositoryImpl
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
        val result = tripRepository.addTrip(sampleTrip)
        assertTrue(result)
        assertEquals(1, tripRepository.getAllTrips().size)
        assertEquals(sampleTrip, tripRepository.getTripById("1"))
    }

    @Test
    fun `addTrip should fail when id already exists`() {
        tripRepository.addTrip(sampleTrip)
        val result = tripRepository.addTrip(sampleTrip)
        assertFalse(result)
        assertEquals(1, tripRepository.getAllTrips().size)
    }

    @Test
    fun `updateTrip should update an existing trip`() {
        tripRepository.addTrip(sampleTrip)
        val updatedTrip = sampleTrip.copy(destination = "Madrid", budget = 2000.0)
        val result = tripRepository.updateTrip(updatedTrip)
        assertTrue(result)
        assertEquals("Madrid", tripRepository.getTripById("1")?.destination)
        assertEquals(2000.0, tripRepository.getTripById("1")?.budget)
    }

    @Test
    fun `updateTrip should fail when trip does not exist`() {
        val result = tripRepository.updateTrip(sampleTrip)
        assertFalse(result)
    }

    @Test
    fun `deleteTrip should remove the trip if it exists`() {
        tripRepository.addTrip(sampleTrip)
        val result = tripRepository.deleteTrip("1")
        assertTrue(result)
        assertEquals(0, tripRepository.getAllTrips().size)
    }

    @Test
    fun `deleteTrip should fail when trip does not exist`() {
        val result = tripRepository.deleteTrip("1")
        assertFalse(result)
    }
}