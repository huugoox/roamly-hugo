package com.example.navigation.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.navigation.NavController
import com.example.navigation.R

import com.example.navigation.data.UserRepository
import com.example.navigation.domain.models.User

enum class ProfileSection {
    ROUTES, FAVORITES, COMPLETED
}

@Composable
fun ProfileScreen(navController: NavController) {
    val user = UserRepository.currentUser
    var selectedSection by remember { mutableStateOf(ProfileSection.ROUTES) }

    Scaffold(
        topBar = { ProfileTopBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            ProfileHeader(user)
            ProfileStats(user)
            ProfileTabs(selectedSection) { selectedSection = it }

            when (selectedSection) {
                ProfileSection.ROUTES -> UserTripsScreen()
                ProfileSection.FAVORITES -> FavoriteTripsScreen()
                ProfileSection.COMPLETED -> CompletedTripsScreen()
            }
        }
    }
}

@Composable
fun ProfileHeader(user: User) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.reshot_icon_crops_46sre7cn8u),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column (modifier = Modifier.weight(1f)) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    user.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )


            }
            Text(
                user.bio,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ProfileStats(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(value = user.uploadedRoutes, label = "Rutas")
        StatItem(value = user.followers, label = "Seguidores")
        StatItem(value = user.following, label = "Seguidos")
        StatItem(value = user.totalLikes, label = "Likes")
    }
}

@Composable
fun StatItem(value: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("$value", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(label, fontSize = 10.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileTabs(selectedTab: ProfileSection, onTabSelected: (ProfileSection) -> Unit) {
    val tabs = listOf("Posts" to ProfileSection.ROUTES, "Favorites" to ProfileSection.FAVORITES, "Completed" to ProfileSection.COMPLETED)

    TabRow(
        selectedTabIndex = tabs.indexOfFirst { it.second == selectedTab },
        containerColor = Color.White,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                color = Color.Black,
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabs.indexOfFirst { it.second == selectedTab}])
            )
        }
    ) {
        tabs.forEach { (title, tab) ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        title,
                        fontSize = 14.sp,
                        fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Medium,
                        color = Color.Black
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(alpha = 0.6f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Profile", color = Color.Black) },
        actions = {
            IconButton(onClick = { navController.navigate("settings") {
                popUpTo("profile") { inclusive = true }
            }
            }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Black)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}