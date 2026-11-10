package com.example.evenpulse.domain.model

enum class Stage(val displayName: String) {
    FLOW("FLOW STAGE"),
    SAMSUNG("SAMSUNG STAGE"),
    ALTERNATIVE("ALTERNATIVE STAGE"),
    PERRYS("PERRY'S STAGE")
}

enum class FestivalDay(val displayName: String) {
    FRIDAY("VIERNES 12"),
    SATURDAY("SÁBADO 13"),
    SUNDAY("DOMINGO 14")
}

data class Artist(
    val id: String,
    val name: String,
    val startTime: String,
    val stage: Stage,
    val day: FestivalDay,
    val isFavorite: Boolean = false
)
