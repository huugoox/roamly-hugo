package com.example.roamly.domain.repository

import com.example.roamly.domain.models.Image

interface ImageRepository {
    fun uploadPhoto(image: Image): Boolean
    fun getAllImages(): List<Image>
    fun getImageById(id: Int): Image?
    fun updateImage(image: Image): Boolean
    fun deleteImage(id: Int): Boolean
}