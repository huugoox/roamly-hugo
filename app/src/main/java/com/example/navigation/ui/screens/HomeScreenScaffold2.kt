package com.example.navigation.ui.screens

import  com.example.navigation.data.TripRepository
import com.example.navigation.models.Trip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

enum class PlanningMode {
    EXPLORE, SEARCH, PROFILE
}

@Composable
fun HomeScreenScaffold2(navController: NavController) {
    var selectedMode by remember { mutableStateOf(PlanningMode.PROFILE) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedMode) { selectedMode = it }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            when (selectedMode) {
                PlanningMode.EXPLORE -> GlobeScreen()
                PlanningMode.SEARCH -> SearchScreen()
                PlanningMode.PROFILE -> ProfileScreen(navController)

            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedMode: PlanningMode, onModeSelected: (PlanningMode) -> Unit) {
    NavigationBar (containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE0E0E0), RectangleShape)
        ){
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Explore, contentDescription = "Explore", tint = Color.Black) },
            label = { Text("Explore", color = Color.Black) },
            selected = selectedMode == PlanningMode.EXPLORE,
            onClick = { onModeSelected(PlanningMode.EXPLORE) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Black) },
            label = { Text("Search", color = Color.Black) },
            selected = selectedMode == PlanningMode.SEARCH,
            onClick = { onModeSelected(PlanningMode.SEARCH) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.Black) },
            label = { Text("Profile", color = Color.Black) },
            selected = selectedMode == PlanningMode.PROFILE,
            onClick = { onModeSelected(PlanningMode.PROFILE) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.2f)
            )
        )
    }
}