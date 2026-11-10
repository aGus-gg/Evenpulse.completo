package com.example.evenpulse.presentation.navigation.screens

import androidx.lifecycle.ViewModel
import com.example.evenpulse.domain.model.Artist
import com.example.evenpulse.domain.model.FestivalDay
import com.example.evenpulse.domain.model.Stage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FestivalUiState(
    val selectedDay: FestivalDay = FestivalDay.FRIDAY,
    val artists: List<Artist> = emptyList()
)

class FestivalViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FestivalUiState())
    val uiState: StateFlow<FestivalUiState> = _uiState.asStateFlow()

    init {
        // Datos simulados basados en las imágenes de referencia
        _uiState.value = FestivalUiState(
            artists = listOf(
                Artist("1", "REYBRUJAS", "13 HS", Stage.SAMSUNG, FestivalDay.FRIDAY),
                Artist("2", "LUDMILA DI PASCUALE", "13 HS", Stage.PERRYS, FestivalDay.FRIDAY),
                Artist("3", "143LETI", "14 HS", Stage.FLOW, FestivalDay.FRIDAY),
                Artist("4", "MASSACRE", "15 HS", Stage.SAMSUNG, FestivalDay.FRIDAY),
                Artist("5", "SABRINA CARPENTER", "22 HS", Stage.FLOW, FestivalDay.FRIDAY),
                Artist("6", "ADELA", "13 HS", Stage.FLOW, FestivalDay.SUNDAY),
                Artist("7", "RUEL", "17 HS", Stage.ALTERNATIVE, FestivalDay.SUNDAY)
            )
        )
    }

    fun selectDay(day: FestivalDay) {
        _uiState.update { it.copy(selectedDay = day) }
    }

    fun toggleFavorite(artistId: String) {
        _uiState.update { currentState ->
            val updatedArtists = currentState.artists.map { artist ->
                if (artist.id == artistId) artist.copy(isFavorite = !artist.isFavorite) else artist
            }
            currentState.copy(artists = updatedArtists)
        }
    }
}
