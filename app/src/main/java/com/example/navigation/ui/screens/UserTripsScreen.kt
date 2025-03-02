package com.example.navigation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navigation.R
import com.example.navigation.data.TripRepository
import com.example.navigation.data.UserRepository
import com.example.navigation.models.Trip
import com.example.navigation.ui.components.TripCard
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun UserTripsScreen() {
    val user = UserRepository.currentUser
    val trips = TripRepository.trips.take(user.uploadedRoutes)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        if (trips.isEmpty()) {
            EmptyStateMessage("No has subido ninguna ruta todavía.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(trips) { trip ->
                    TripCard(trip)
                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 6.dp))
                }
            }
        }
    }
}

@Composable
fun EmptyStateMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
    }
}
