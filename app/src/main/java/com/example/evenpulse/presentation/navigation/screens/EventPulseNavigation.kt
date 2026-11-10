package com.example.evenpulse.presentation.navigation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

@Composable
fun EventPulseApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute != "login"
    val festivalViewModel: FestivalViewModel = viewModel()

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = Color.White) {
                    val items = listOf(
                        Pair("login", "INICIO"),
                        Pair("lineup", "LINE UP"),
                        Pair("map", "MAPA"),
                        Pair("favorites", "FAVORITOS")
                    )
                    items.forEach { (route, title) ->
                        NavigationBarItem(
                            selected = currentRoute == route,
                            onClick = {
                                if(route != "login") {
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                } else {
                                    navController.navigate("login") { popUpTo(0) }
                                }
                            },
                            icon = { },
                            label = { Text(title, fontWeight = FontWeight.Bold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = Color(0xFFD4E157),
                                unselectedTextColor = Color.Black
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "login", modifier = Modifier.padding(innerPadding)) {
            composable("login") { LoginScreen(navController) }
            composable("lineup") { LineUpScreen(festivalViewModel) }
            composable("map") { MapScreen() }
            composable("favorites") { FavoritesScreen(festivalViewModel) }
        }
    }
}
