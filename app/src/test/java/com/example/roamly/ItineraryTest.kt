//package com.example.roamly.domain
//import com.example.roamly.domain.models.ItineraryItem
//import com.example.roamly.data.repository.ItineraryRepositoryImpl
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import java.text.SimpleDateFormat
//import java.util.Date
//
//class ItineraryRepositoryImplTest {
//
//    private lateinit var itineraryRepository: ItineraryRepositoryImpl
//    private lateinit var sampleItineraryItem: ItineraryItem
//    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//
//    @Before
//    fun setup() {
//        itineraryRepository = ItineraryRepositoryImpl()
//        val itemDate: Date = dateFormat.parse("2025-03-15")!!
//        sampleItineraryItem = ItineraryItem(
//            id = "1",
//            tripId = "1",
//            description = "Explore Park Guell in Barcelona",
//            date = itemDate,
//            location = "Barcelona, Spain"
//        )
//    }
//
//    @Test
//    fun `addItineraryItem should add item successfully when id is unique`() {
//        val result = itineraryRepository.addItineraryItem(sampleItineraryItem)
//        assertTrue(result)
//        assertEquals(1, itineraryRepository.getItineraryItems("1").size)
//        assertEquals(sampleItineraryItem, itineraryRepository.getItineraryItemById("1"))
//    }
//
//    @Test
//    fun `addItineraryItem should fail when id already exists`() {
//        itineraryRepository.addItineraryItem(sampleItineraryItem)
//        val result = itineraryRepository.addItineraryItem(sampleItineraryItem)
//        assertFalse(result)
//        assertEquals(1, itineraryRepository.getItineraryItems("1").size)
//    }
//
//    @Test
//    fun `getItineraryItems should return a sorted list by date`() {
//        val item2 = sampleItineraryItem.copy(id = "2", description = "Visit La Sagrada Familia", date = dateFormat.parse("2025-03-14")!!)
//        itineraryRepository.addItineraryItem(sampleItineraryItem)
//        itineraryRepository.addItineraryItem(item2)
//        val items = itineraryRepository.getItineraryItems("1")
//        assertEquals(2, items.size)
//        assertEquals("Visit La Sagrada Familia", items[0].description)
//        assertEquals("Explore Park Guell in Barcelona", items[1].description)
//    }
//
//    @Test
//    fun `getItineraryItemById should return the correct item by id`() {
//        itineraryRepository.addItineraryItem(sampleItineraryItem)
//        val result = itineraryRepository.getItineraryItemById("1")
//        assertNotNull(result)
//        assertEquals(sampleItineraryItem, result)
//    }
//
//    @Test
//    fun `getItineraryItemById should return null if item does not exist`() {
//        val result = itineraryRepository.getItineraryItemById("999")
//        assertNull(result)
//    }
//
//    @Test
//    fun `updateItineraryItem should update an existing item`() {
//        itineraryRepository.addItineraryItem(sampleItineraryItem)
//        val updatedItem = sampleItineraryItem.copy(description = "Visit Park Guell - Updated", location = "Barcelona, Spain - Updated")
//        val result = itineraryRepository.updateItineraryItem(updatedItem)
//        assertTrue(result)
//        assertEquals("Visit Park Guell - Updated", itineraryRepository.getItineraryItemById("1")?.description)
//        assertEquals("Barcelona, Spain - Updated", itineraryRepository.getItineraryItemById("1")?.location)
//    }
//
//    @Test
//    fun `updateItineraryItem should fail when item does not exist`() {
//        val result = itineraryRepository.updateItineraryItem(sampleItineraryItem)
//        assertFalse(result)
//    }
//
//    @Test
//    fun `deleteItineraryItem should remove the item if it exists`() {
//        itineraryRepository.addItineraryItem(sampleItineraryItem)
//        val result = itineraryRepository.deleteItineraryItem("1")
//        assertTrue(result)
//        assertEquals(0, itineraryRepository.getItineraryItems("1").size)
//    }
//
//    @Test
//    fun `deleteItineraryItem should fail when item does not exist`() {
//        val result = itineraryRepository.deleteItineraryItem("1")
//        assertFalse(result)
//    }
//}