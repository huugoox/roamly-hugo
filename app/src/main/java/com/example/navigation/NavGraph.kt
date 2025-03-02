package com.example.navigation


import ConfigureProfileScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.navigation.ui.screens.AboutScreen
import com.example.navigation.ui.screens.HomeScreenScaffold2
import com.example.navigation.ui.screens.LoginScreen2
import com.example.navigation.ui.screens.ProfileScreen
import com.example.navigation.ui.screens.TermsAndConditionsScreen




@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen2(navController) }
        composable("home") { HomeScreenScaffold2(navController)
        }
        composable("profile") { ProfileScreen(navController) }
        composable("profile/{id}",
                arguments = listOf(navArgument("id"){
                            type = NavType.IntType
                })
        ) {
            val userId = it.arguments?.getInt("id") ?: -1
            ProfileScreen(navController)
        }
        composable("profileMenu") { ProfileScreen(navController) }
        composable("about") { AboutScreen(navController)  }
        composable("termsAndConditions"){ TermsAndConditionsScreen(navController) }
        composable ("settings"){ SettingsScreen(navController)  }
        composable("configureProfile") { ConfigureProfileScreen(navController)}
    }
}
