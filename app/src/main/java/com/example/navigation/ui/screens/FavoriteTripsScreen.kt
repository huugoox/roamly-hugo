package com.example.navigation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.R
import com.example.navigation.data.TripRepository
import com.example.navigation.models.Trip

@Composable
fun FavoriteTripsScreen() {
    val favoriteTrips = TripRepository.trips.take(2) // Simulación de favoritos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text(
            text = "Your Favorite Trips ❤️",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteTrips.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No favorite trips yet!", fontSize = 18.sp, color = Color.Gray)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(favoriteTrips.size) { index ->
                    TripCard(trip = favoriteTrips[index])
                }
            }
        }
    }
}

@Composable
fun TripCard(trip: Trip) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { /* @ToDo*/ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.reshot_icon_spotify_ge2jyxascz),
                contentDescription = trip.destination,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Detalles del viaje
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = trip.destination, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${trip.startDate} - ${trip.endDate}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.Red)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Added to favorites", fontSize = 14.sp, color = Color(0xFFE91E63))
                }
            }
        }
    }
}
