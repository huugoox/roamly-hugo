package com.example.roamly.data.local.mapper

import com.example.roamly.data.local.entity.ItineraryItemEntity
import com.example.roamly.data.local.entity.TripEntity
import com.example.roamly.data.local.entity.UserEntity
import com.example.roamly.domain.models.ItineraryItem
import com.example.roamly.domain.models.Trip
import com.example.roamly.domain.models.User
import java.util.Date

fun Trip.toEntity(): TripEntity =
    TripEntity(
        id = id,
        destination = destination,
        startDate = startDate.time,
        endDate = endDate.time,
        budget = budget,
        notes = notes,
        isFavorite = isFavorite,
        coverImageUrl = coverImageUrl
    )

fun ItineraryItem.toEntity(): ItineraryItemEntity =
    ItineraryItemEntity(
        id = id,
        tripId = tripId,
        description = description,
        date = date.time,
        location = location,
    )

fun TripEntity.toDomain(itineraryItems: List<ItineraryItem>? = emptyList()): Trip =
    Trip(
        id = id,
        destination = destination,
        startDate = Date(startDate),
        endDate = Date(endDate),
        budget = budget,
        notes = notes,
        isFavorite = isFavorite,
        coverImageUrl = coverImageUrl,
        itineraryItems = itineraryItems.orEmpty()
    )

fun ItineraryItemEntity.toDomain(): ItineraryItem =
    ItineraryItem(
        id = id,
        tripId = tripId,
        description = description,
        date = Date(date),
        location = location,
    )

fun UserEntity.toDomain(): User =
    User(
        id = id,
        fullName = fullName,
        email = email,
        username = username,
        password = password,
        birthdate = birthdate,
        address = address,
        country = country,
        phoneNumber = phoneNumber,
        acceptEmails = acceptEmails,
        uploadedRoutes = uploadedRoutes,
        followers = followers,
        following = following,
        totalLikes = totalLikes
    )

// Mapper para convertir de User a UserEntity (entidad de base de datos)
fun User.toEntity(): UserEntity =
    UserEntity(
        id = id,
        fullName = fullName,
        email = email,
        username = username,
        password = password,
        birthdate = birthdate,
        address = address,
        country = country,
        phoneNumber = phoneNumber,
        acceptEmails = acceptEmails,
        uploadedRoutes = uploadedRoutes,
        followers = followers,
        following = following,
        totalLikes = totalLikes
    )
