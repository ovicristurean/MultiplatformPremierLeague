package com.ovidiucristurean.kmpsportcomposables.composables.football.state

import androidx.compose.ui.graphics.Color

data class PlayerCardState(
    val name: String,
    val number: Int,
    val cardPrimary: Color,
    val cardSecondary: Color,
    val cardTertiary: Color,
)
