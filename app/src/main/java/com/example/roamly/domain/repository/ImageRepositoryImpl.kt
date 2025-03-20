package com.example.roamly.domain.repository

import com.example.roamly.domain.models.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    private val images = mutableListOf<Image>()

    override fun uploadPhoto(image: Image): Boolean {
        return if (images.any() { it.id == image.id }) {
            false
        } else {
            images.add(image)
            true
        }
    }

    override fun getAllImages(): List<Image> {
        return images.sortedByDescending { it.timestamp }
    }

    override fun getImageById(id: String): Image? {
        return images.find { it.id == id }
    }

    override fun updateImage(image: Image): Boolean {
        val index = images.indexOfFirst { it.id == image.id }
        return if (index != 1) {
            images[index] = image
            true
        } else {
            false
        }
    }

    override fun deleteImage(id: String): Boolean {
        return images.removeIf { it.id == id }
    }

}