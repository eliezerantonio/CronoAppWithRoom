package com.example.cronoapp_e_room.state

data class CronoState(
    val activeCrono: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String = "",
)