package com.example.navigation.domain.repository

import com.example.navigation.domain.models.Image
import javax.inject.Inject

interface ImageRepository {
    fun uploadPhoto(image: Image): Boolean
    fun getAllImages(): List<Image>
    fun getImageById(id: String): Image?
    fun updateImage(image: Image): Boolean
    fun deleteImage(id: String): Boolean
}