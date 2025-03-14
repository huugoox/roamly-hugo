package com.example.navigation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun CompletedTripsScreen() {
    var completedTrips by remember { mutableStateOf(listOf<Trip>()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val newTrip = Trip(
                    destination = "Nueva Ciudad",
                    startDate = Date(),
                    endDate = Date()
                )
                completedTrips = completedTrips + newTrip
            }) {
                Text("+", fontSize = 24.sp, color = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(padding)
                .padding(16.dp)
        ) {
            if (completedTrips.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No completed trips yet!", fontSize = 18.sp, color = Color.Gray)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(completedTrips.size) { index ->
                        CompletedTripCard(trip = completedTrips[index])
                    }
                }
            }
        }
    }
}

@Composable
fun CompletedTripCard(trip: Trip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = trip.destination, fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${trip.startDate} - ${trip.endDate}", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

data class Trip(val destination: String, val startDate: Date, val endDate: Date)
