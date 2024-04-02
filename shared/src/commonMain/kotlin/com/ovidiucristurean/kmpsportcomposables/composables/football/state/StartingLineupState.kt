package com.ovidiucristurean.kmpsportcomposables.composables.football.state

import androidx.compose.ui.graphics.Color

data class StartingLineupState(
    val playerLines: List<List<PlayerInfoCardState>>,
    val automaticRevealState: AutomaticRevealState,
    val playerCardColors: PlayerCardColors
)

sealed interface AutomaticRevealState {
    data object OnClick : AutomaticRevealState

    data class Automatically(
        val delayBetweenLines: Int
    ) : AutomaticRevealState
}

data class PlayerCardColors(
    val cardPrimary: Color,
    val cardSecondary: Color,
    val cardTertiary: Color
)
