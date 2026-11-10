package com.example.evenpulse.presentation.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evenpulse.domain.model.FestivalDay

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lollapalooza", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Black)
        Text("ARGENTINA", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(bottom = 48.dp))

        Text("COMPLETA TU PERFIL", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = "", onValueChange = {},
            label = { Text("NOMBRE O CORREO ELECTRÓNICO", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth().background(Color.White),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "", onValueChange = {},
            label = { Text("CONTRASEÑA", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth().background(Color.White),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("lineup") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4E157)),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("INGRESAR", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LineUpScreen(viewModel: FestivalViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val artistsForDay = uiState.artists.filter { it.day == uiState.selectedDay }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF4DD0E1))
    ) {
        Text("CRONOGRAMA", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black, modifier = Modifier.fillMaxWidth().background(Color.Black).padding(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FestivalDay.entries.forEach { day ->
                val bgColor = when(day) {
                    FestivalDay.FRIDAY -> Color(0xFFF48FB1)
                    FestivalDay.SATURDAY -> Color(0xFFD4E157)
                    FestivalDay.SUNDAY -> Color(0xFFFFF59D)
                }
                Button(
                    onClick = { viewModel.selectDay(day) },
                    colors = ButtonDefaults.buttonColors(containerColor = if (uiState.selectedDay == day) bgColor else Color.DarkGray)
                ) {
                    Text(day.displayName, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(artistsForDay) { artist ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { viewModel.toggleFavorite(artist.id) },
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(artist.startTime, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text(artist.stage.displayName, color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(1f))
                        Text(artist.name, color = if (artist.isFavorite) Color(0xFFD4E157) else Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritesScreen(viewModel: FestivalViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val favoriteArtists = uiState.artists.filter { it.isFavorite && it.day == uiState.selectedDay }.sortedBy { it.startTime }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFAEEA00))
    ) {
        Text("FAVORITOS", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black, modifier = Modifier.fillMaxWidth().background(Color.Black).padding(16.dp))
        Text(uiState.selectedDay.displayName, fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.Black, modifier = Modifier.padding(16.dp))

        if (favoriteArtists.isEmpty()) {
            Text("No hay favoritos seleccionados.", modifier = Modifier.padding(16.dp), color = Color.Black)
        } else {
            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                items(favoriteArtists) { artist ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black)
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(artist.startTime, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(artist.stage.displayName, color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(1f))
                            Text(artist.name, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MapScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF4DD0E1)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MAPA", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black, modifier = Modifier.fillMaxWidth().background(Color.Black).padding(16.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("¿DÓNDE TE ENCONTRÁS?", fontSize = 24.sp, fontWeight = FontWeight.Black, color = Color(0xFFF48FB1))
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray), contentAlignment = Alignment.Center) {
                    Text("[IMAGEN DEL MAPA AQUÍ]", color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Puestos de Bebidas (Rosa)\nComida (Naranja)\nInfo (Celeste)", color = Color.Black, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
