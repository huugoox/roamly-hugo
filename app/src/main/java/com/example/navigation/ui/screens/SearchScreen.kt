package com.example.navigation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val filteredTrips = TripRepository.trips.filter { it.destination.contains(searchQuery, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            label = { Text("Search destinations", fontSize = 16.sp) },
            leadingIcon = {Icon(Icons.Filled.Search, contentDescription = "Search Icon")},
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFD1D5DB),
                focusedBorderColor = Color(0xFF3B82F6),
                cursorColor = Color(0xFF3B82F6)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp)){
            items(filteredTrips.size) { index ->
                TripItem(filteredTrips[index])
            }
        }
    }
    if (filteredTrips.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No destinations found", fontSize = 18.sp, color = Color.Gray)
        }
    }
}

@Composable
fun TripItem(trip: Trip) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable{/* @ToDo */}
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ){
        Column {
            Image(
                painter = painterResource(R.drawable.reshot_icon_crops_46sre7cn8u),
                contentDescription = trip.destination,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = trip.destination, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${trip.startDate} - ${trip.endDate}", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Budget: $${trip.budget}", fontSize = 14.sp, color = Color(0xFF10B981))
            }
        }
    }

}
