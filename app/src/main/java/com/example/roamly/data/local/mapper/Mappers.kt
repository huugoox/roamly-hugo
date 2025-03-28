package com.example.roamly.data.local.mapper

import com.example.roamly.data.local.entity.ItineraryItemEntity
import com.example.roamly.data.local.entity.TripEntity
import com.example.roamly.domain.models.ItineraryItem
import com.example.roamly.domain.models.Trip
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