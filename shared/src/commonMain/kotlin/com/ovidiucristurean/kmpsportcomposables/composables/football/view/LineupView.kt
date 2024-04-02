package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.AutomaticRevealState
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.StartingLineupState
import com.ovidiucristurean.kmpsportcomposables.getScreenSize
import kotlinx.coroutines.delay

@Composable
fun LineupView(
    lineup: StartingLineupState,
) {
    var lineAlphas by remember {
        mutableStateOf(List(lineup.playerLines.size) { 1f })
    }

    var automaticRevealLines by remember {
        mutableStateOf(List(lineup.playerLines.size) { false })
    }

    LaunchedEffect(Unit) {
        if (lineup.automaticRevealState is AutomaticRevealState.Automatically) {
            delay(LINE_APPEARANCE_DELAY.toLong() * (automaticRevealLines.size - 1))
            delay(lineup.automaticRevealState.delayBetweenLines.toLong())
            automaticRevealLines = List(automaticRevealLines.toMutableList().size) { it == 0 }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        lineup.playerLines.forEachIndexed { index, line ->
            val scaledPlayerWidth = computeScaledWidth(line.size)
            PlayerCardLine(
                playerCards = line,
                appearanceDelay = LINE_APPEARANCE_DELAY * index,
                scaledPlayerWidth = scaledPlayerWidth,
                cardsAlpha = lineAlphas[index],
                zIndex = lineAlphas[index],
                onLineSelected = {
                    val lineAlphasCopy = MutableList(lineup.playerLines.size) { 0.3f }
                    lineAlphasCopy[index] = 1f
                    lineAlphas = lineAlphasCopy
                },
                onRevealDone = {
                    lineAlphas = List(lineup.playerLines.size) { 1f }

                    if (lineup.automaticRevealState is AutomaticRevealState.Automatically) {
                        val automaticAppearanceCopy = automaticRevealLines.toMutableList()
                        if (index < lineup.playerLines.size - 1) {
                            automaticAppearanceCopy[index] = false
                            automaticAppearanceCopy[index + 1] = true
                            automaticRevealLines = automaticAppearanceCopy
                        }
                    }
                },
                isAutomaticRevealEnabled = automaticRevealLines[index],
                playerCardColors = lineup.playerCardColors
            )
        }
    }
}

/**
 * calculate scale so that all cards fit horizontally even for the longest line
 */
@Composable
private fun computeScaledWidth(numberOfPlayers: Int): Float {
    val widthDp = with(LocalDensity.current) {
        getScreenSize().widthPx.toDp()
    }
    return when (numberOfPlayers) {
        1 -> widthDp.value / 2
        2 -> widthDp.value / 3
        3 -> widthDp.value / 4
        else -> widthDp.value / numberOfPlayers - 8.dp.value
    }
}

const val LINE_APPEARANCE_DELAY = 250
