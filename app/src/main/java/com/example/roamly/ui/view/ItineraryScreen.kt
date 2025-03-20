package com.example.roamly.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roamly.domain.models.ItineraryItem
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ItineraryScreen(navController: NavController, tripId: String) {
    var itineraryItems by remember { mutableStateOf(listOf<ItineraryItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var activityName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var activityDate by remember { mutableStateOf(Date()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+", fontSize = 24.sp, color = Color.White)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            if (itineraryItems.isEmpty()) {
                Text("No itinerary items yet!", fontSize = 18.sp, modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn {
                    items(itineraryItems.size) { index ->
                        ItineraryItemCard(itineraryItems[index])
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Itinerary Item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = activityName,
                            onValueChange = { activityName = it },
                            label = { Text("Activity Name") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = location,
                            onValueChange = { location = it },
                            label = { Text("Location") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (activityName.isNotEmpty() && location.isNotEmpty()) {
                            itineraryItems = itineraryItems + ItineraryItem(
                                id = UUID.randomUUID().toString(),
                                tripId = tripId,
                                date = activityDate,
                                location = location,
                                timestamp = TODO(),
                                description = TODO()
                            )
                            showDialog = false
                        }
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}
@Composable
fun ItineraryItemCard(item: ItineraryItem) {
    val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.id, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Location: ${item.location}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Date: ${formatter.format(item.date)}", fontSize = 14.sp)
        }
    }
}
