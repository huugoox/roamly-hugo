package com.example.roamly.domain
import com.example.roamly.domain.models.ItineraryItem
import com.example.roamly.data.repository.ItineraryRepositoryImpl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class ItineraryRepositoryImplTest {

    private lateinit var itineraryRepository: ItineraryRepositoryImpl
    private lateinit var sampleItineraryItem: ItineraryItem
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    @Before
    fun setup() {
        // Inicializa el repositorio y un objeto ItineraryItem de ejemplo con todos los campos requeridos
        itineraryRepository = ItineraryRepositoryImpl()

        val itemDate: Date = dateFormat.parse("2025-03-15")!!

        // El nuevo modelo de ItineraryItem incluye un campo timestamp
        sampleItineraryItem = ItineraryItem(
            id = "1",
            tripId = "1",
            description = "Explore Park Guell in Barcelona",
            date = itemDate,
            location = "Barcelona, Spain"
            // timestamp se utiliza por defecto, no es necesario asignarlo manualmente
        )
    }

    @Test
    fun `addItineraryItem should add item successfully when id is unique`() {
        // Act
        val result = itineraryRepository.addItineraryItem(sampleItineraryItem)

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque el itinerario se añadió correctamente
        assertEquals(1, itineraryRepository.getItineraryItems("1").size)  // Debe haber un ítem en la lista
        assertEquals(sampleItineraryItem, itineraryRepository.getItineraryItemById("1"))  // El ítem agregado debe coincidir
    }

    @Test
    fun `addItineraryItem should fail when id already exists`() {
        // Arrange
        itineraryRepository.addItineraryItem(sampleItineraryItem)

        // Act
        val result = itineraryRepository.addItineraryItem(sampleItineraryItem)  // Intentamos agregar el mismo ítem otra vez

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el ítem ya existe
        assertEquals(1, itineraryRepository.getItineraryItems("1").size)  // La lista debe tener solo un ítem
    }

    @Test
    fun `getItineraryItems should return a sorted list by date`() {
        // Arrange
        val item2 = sampleItineraryItem.copy(id = "2", description = "Visit La Sagrada Familia", date = dateFormat.parse("2025-03-14")!!)
        itineraryRepository.addItineraryItem(sampleItineraryItem)
        itineraryRepository.addItineraryItem(item2)

        // Act
        val items = itineraryRepository.getItineraryItems("1")

        // Assert
        assertEquals(2, items.size)  // Debe haber dos ítems
        assertEquals("Visit La Sagrada Familia", items[0].description)  // El primer ítem debe ser el de la fecha más temprana
        assertEquals("Explore Park Guell in Barcelona", items[1].description)  // El segundo ítem debe ser el de la fecha más tardía
    }

    @Test
    fun `getItineraryItemById should return the correct item by id`() {
        // Arrange
        itineraryRepository.addItineraryItem(sampleItineraryItem)

        // Act
        val result = itineraryRepository.getItineraryItemById("1")

        // Assert
        assertNotNull(result)  // El ítem debe ser encontrado
        assertEquals(sampleItineraryItem, result)  // El ítem debe coincidir con el original
    }

    @Test
    fun `getItineraryItemById should return null if item does not exist`() {
        // Act
        val result = itineraryRepository.getItineraryItemById("999")

        // Assert
        assertNull(result)  // El ítem no debe ser encontrado
    }

    @Test
    fun `updateItineraryItem should update an existing item`() {
        // Arrange
        itineraryRepository.addItineraryItem(sampleItineraryItem)
        val updatedItem = sampleItineraryItem.copy(description = "Visit Park Guell - Updated", location = "Barcelona, Spain - Updated")

        // Act
        val result = itineraryRepository.updateItineraryItem(updatedItem)

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque el ítem fue actualizado
        assertEquals("Visit Park Guell - Updated", itineraryRepository.getItineraryItemById("1")?.description)  // El título debe ser actualizado
        assertEquals("Barcelona, Spain - Updated", itineraryRepository.getItineraryItemById("1")?.location)  // La ubicación debe ser actualizada
    }

    @Test
    fun `updateItineraryItem should fail when item does not exist`() {
        // Act
        val result = itineraryRepository.updateItineraryItem(sampleItineraryItem)

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el ítem no existe
    }

    @Test
    fun `deleteItineraryItem should remove the item if it exists`() {
        // Arrange
        itineraryRepository.addItineraryItem(sampleItineraryItem)

        // Act
        val result = itineraryRepository.deleteItineraryItem("1")

        // Assert
        assertTrue(result)  // El resultado debe ser true, porque el ítem fue eliminado
        assertEquals(0, itineraryRepository.getItineraryItems("1").size)  // La lista debe estar vacía
    }

    @Test
    fun `deleteItineraryItem should fail when item does not exist`() {
        // Act
        val result = itineraryRepository.deleteItineraryItem("1")

        // Assert
        assertFalse(result)  // El resultado debe ser false, porque el ítem no existía
    }
}
