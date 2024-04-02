package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerCardState
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerInfoCardState
import com.ovidiucristurean.kmpsportcomposables.getScreenSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlayerCard(
    playerInfoCardState: PlayerInfoCardState,
    appearanceDelay: Int,
    cardWidth: Dp,
    cardHeight: Dp,
    playerCardRevealState: PlayerCardRevealState,
    playerCardAnimationState: PlayerCardAnimationState,
    scaleFactor: Float,
    cardAlpha: Float,
    onPlayerCardSelected: () -> Unit,
    onPlayerCardRevealedStateChanged: (PlayerCardRevealState) -> Unit,
    onPlayerCardAnimationStateChanged: (PlayerCardAnimationState) -> Unit,
    isAutomaticAppearanceEnabled: Boolean = false,
    cardPrimary: Color,
    cardSecondary: Color,
    cardTertiary: Color,
) {
    val screenHeightPx = getScreenSize().heightPx
    var isPlayerCardVisible by remember { mutableStateOf(false) }
    val cardHeightPx = with(LocalDensity.current) { cardHeight.toPx() }
    var yCoordinate by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    val onPlayerCardClicked = {
        onPlayerCardSelected()
        scope.launch {
            onPlayerCardAnimationStateChanged(PlayerCardAnimationState.START_FLIP_CARD_ANIMATION)
            delay(START_ANIMATION_REVEAL_DURATION.toLong())
            onPlayerCardAnimationStateChanged(PlayerCardAnimationState.FINISH_FLIP_CARD_ANIMATION)
            onPlayerCardRevealedStateChanged(PlayerCardRevealState.REVEALED)
            delay(START_ANIMATION_REVEAL_DURATION.toLong())
        }
    }

    LaunchedEffect(isAutomaticAppearanceEnabled) {
        isPlayerCardVisible = true
        if (isAutomaticAppearanceEnabled) {
            delay(1000)
            onPlayerCardClicked()
        }
    }

    AnimatedVisibility(
        visible = isPlayerCardVisible,
        enter = scaleIn(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = appearanceDelay
            )
        )
    ) {
        val transition = updateTransition(playerCardAnimationState, label = "cardTransition")
        val translation by transition.animateFloat(
            label = "translateTransition",
            transitionSpec = {
                tween(START_ANIMATION_REVEAL_DURATION)
            }
        ) {
            when (it) {
                PlayerCardAnimationState.NOT_ANIMATING -> {
                    0f
                }

                PlayerCardAnimationState.START_FLIP_CARD_ANIMATION -> {
                    screenHeightPx / 2 - cardHeightPx / 2 - yCoordinate
                }

                PlayerCardAnimationState.FINISH_FLIP_CARD_ANIMATION -> {
                    screenHeightPx / 2 - cardHeightPx / 2 - yCoordinate
                }

                PlayerCardAnimationState.REVEAL_DONE -> {
                    0f
                }
            }
        }
        val cardScale by transition.animateFloat(label = "scaleTransition",
            transitionSpec = {
                tween(START_ANIMATION_REVEAL_DURATION)
            }) {
            when (it) {
                PlayerCardAnimationState.NOT_ANIMATING -> {
                    1f
                }

                PlayerCardAnimationState.START_FLIP_CARD_ANIMATION -> {
                    scaleFactor
                }

                PlayerCardAnimationState.FINISH_FLIP_CARD_ANIMATION -> {
                    scaleFactor
                }

                PlayerCardAnimationState.REVEAL_DONE -> {
                    1f
                }
            }
        }
        val rotation by transition.animateFloat(label = "rotationTransition",
            transitionSpec = {
                tween(START_ANIMATION_REVEAL_DURATION)
            }) {
            when (it) {
                PlayerCardAnimationState.NOT_ANIMATING -> {
                    0f
                }

                PlayerCardAnimationState.START_FLIP_CARD_ANIMATION -> {
                    -90f
                }

                PlayerCardAnimationState.FINISH_FLIP_CARD_ANIMATION -> {
                    -180f
                }

                PlayerCardAnimationState.REVEAL_DONE -> {
                    -180f
                }
            }
        }

        Box(modifier = Modifier
            .onGloballyPositioned { yCoordinate = it.positionInRoot().y }
            .graphicsLayer {
                translationY = translation
                scaleX = cardScale
                scaleY = cardScale
                rotationY = rotation
                alpha = cardAlpha
            }
        ) {
            when (playerCardRevealState) {
                PlayerCardRevealState.NOT_REVEALED -> {
                    PlayerNotRevealedCard(
                        modifier = Modifier,
                        playerCardState = PlayerCardState(
                            name = playerInfoCardState.lastName,
                            number = playerInfoCardState.number,
                            cardPrimary = cardPrimary,
                            cardSecondary = cardSecondary,
                            cardTertiary = cardTertiary
                        ),
                        width = cardWidth,
                        height = cardHeight,
                        cardPrimary = cardPrimary,
                        cardSecondary = cardSecondary,
                        cardTertiary = cardTertiary,
                        onClick = {
                            onPlayerCardClicked()
                        }
                    )
                }

                PlayerCardRevealState.REVEALED -> {
                    PlayerPresentationCard(
                        modifier = Modifier.graphicsLayer {
                            rotationY = -180f
                        },
                        cardWidth = cardWidth,
                        cardHeight = cardHeight,
                        playerInfoCardState = playerInfoCardState,
                        cardPrimary = cardPrimary,
                        cardSecondary = cardSecondary,
                        onPlayerPresentationFinished = {
                            scope.launch {
                                onPlayerCardAnimationStateChanged(PlayerCardAnimationState.REVEAL_DONE)
                            }
                        }
                    )
                }
            }
        }
    }
}

enum class PlayerCardAnimationState {
    NOT_ANIMATING,
    START_FLIP_CARD_ANIMATION,
    FINISH_FLIP_CARD_ANIMATION,
    REVEAL_DONE
}

enum class PlayerCardRevealState {
    NOT_REVEALED,
    REVEALED
}

const val START_ANIMATION_REVEAL_DURATION = 500
const val FINISH_ANIMATION_REVEAL_DURATION = 2000
const val CARD_QUICK_EXTRA_SCALE = 100
