package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerCardColors
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerInfoCardState

@Composable
fun PlayerCardLine(
    playerCards: List<PlayerInfoCardState>,
    playerCardColors: PlayerCardColors,
    appearanceDelay: Int,
    scaledPlayerWidth: Float,
    cardsAlpha: Float,
    zIndex: Float,
    onLineSelected: () -> Unit,
    onRevealDone: () -> Unit,
    isAutomaticRevealEnabled: Boolean = false
) {
    var playerCardRevealState by remember {
        mutableStateOf(PlayerCardRevealState.NOT_REVEALED)
    }

    var playerCardAnimationState by remember {
        mutableStateOf(PlayerCardAnimationState.NOT_ANIMATING)
    }

    Row(
        modifier = Modifier.fillMaxWidth().zIndex(zIndex),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        playerCards.forEachIndexed { index, playerCard ->
            PlayerCard(
                playerInfoCardState = playerCard,
                cardWidth = 75.dp,
                cardHeight = 120.dp,
                appearanceDelay = appearanceDelay,
                playerCardRevealState = playerCardRevealState,
                playerCardAnimationState = playerCardAnimationState,
                onPlayerCardSelected = {
                    onLineSelected()
                },
                onPlayerCardRevealedStateChanged = { revealState ->
                    playerCardRevealState = revealState
                },
                onPlayerCardAnimationStateChanged = { animationState ->
                    playerCardAnimationState = animationState

                    if (animationState == PlayerCardAnimationState.REVEAL_DONE) {
                        onRevealDone()
                    }
                },
                scaleFactor = adjustScaleFactor(
                    playerCardWidth = 75.dp.value,
                    scaledPlayerCardWidth = scaledPlayerWidth,
                    isWinger = index == 0 || index == playerCards.size - 1
                ),
                cardAlpha = cardsAlpha,
                isAutomaticAppearanceEnabled = isAutomaticRevealEnabled,
                cardPrimary = playerCardColors.cardPrimary,
                cardSecondary = playerCardColors.cardSecondary,
                cardTertiary = playerCardColors.cardTertiary
            )
        }
    }
}

private fun adjustScaleFactor(
    playerCardWidth: Float,
    scaledPlayerCardWidth: Float,
    isWinger: Boolean
): Float {
    return if (isWinger) {
        scaledPlayerCardWidth / playerCardWidth * 0.9f
    } else {
        scaledPlayerCardWidth / playerCardWidth
    }
}
