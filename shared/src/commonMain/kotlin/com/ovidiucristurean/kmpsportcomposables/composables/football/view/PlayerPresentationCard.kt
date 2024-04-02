package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerInfoCardState
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PlayerPresentationCard(
    cardWidth: Dp,
    cardHeight: Dp,
    playerInfoCardState: PlayerInfoCardState,
    cardPrimary: Color,
    cardSecondary: Color,
    onPlayerPresentationFinished: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val rotationAnimation = remember {
        Animatable(0f)
    }

    val brush = Brush.linearGradient(
        colorStops = arrayOf(
            0f to cardPrimary,
            mapTo01(
                rotationAnimation.value,
                CARD_MIN_ROTATION,
                CARD_MAX_ROTATION
            ) to Color.Transparent,
            1f to cardPrimary
        )
    )

    val scaleAnimation = remember {
        Animatable(1f)
    }

    val alphaAnimation = remember {
        Animatable(1f)
    }

    var shakeState by remember {
        mutableStateOf(ShakeState.NOT_SHAKEN)
    }

    val shakeTransition = updateTransition(shakeState, label = "shakeTransition")

    val translationX by shakeTransition.animateFloat(
        label = "transitionXshake",
        transitionSpec = {
            tween(25)
        }) {
        when (it) {
            ShakeState.NOT_SHAKEN -> 0f
            ShakeState.SHAKE_TOP_LEFT -> -30f
            ShakeState.SHAKE_BOTTOM_LEFT -> -30f
            ShakeState.SHAKE_TOP_RIGHT -> 30f
            ShakeState.SHAKE_BOTTOM_RIGHT -> 30f
        }
    }

    val translationY by shakeTransition.animateFloat(
        label = "transitionYshake",
        transitionSpec = {
            tween(25)
        }) {
        when (it) {
            ShakeState.NOT_SHAKEN -> 0f
            ShakeState.SHAKE_TOP_LEFT -> -30f
            ShakeState.SHAKE_BOTTOM_LEFT -> 0f
            ShakeState.SHAKE_TOP_RIGHT -> 30f
            ShakeState.SHAKE_BOTTOM_RIGHT -> 0f
        }
    }

    var isShinyEffectVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(playerInfoCardState.isSpecialPlayer) {
        if (playerInfoCardState.isSpecialPlayer) {
            delay(INITIAL_ANIMATION_DELAY)

            scaleAnimation.animateTo(targetValue = 1.25f, tween(EXTRA_SCALE_ANIMATION_TIME))
            scaleAnimation.animateTo(targetValue = 1f, tween(EXTRA_SCALE_ANIMATION_TIME))

            alphaAnimation.animateTo(targetValue = 0f, tween(EXTRA_ALPHA_ANIMATION_TO_ZERO))
            alphaAnimation.animateTo(targetValue = 1f, tween(EXTRA_ALPHA_ANIMATION_TO_ONE))

            isShinyEffectVisible = true
            (1..NUMBER_OF_SHAKES).forEach {
                shakeState = ShakeState.entries.toTypedArray()[it % ShakeState.entries.size]
                delay(SINGLE_SHAKE_TIME)
            }
            rotationAnimation.animateTo(
                targetValue = CARD_MIN_ROTATION,
                animationSpec = tween(ROTATION_TIME)
            )
            rotationAnimation.animateTo(
                targetValue = CARD_MAX_ROTATION,
                animationSpec = tween(ROTATION_TIME)
            )
            rotationAnimation.animateTo(
                targetValue = CARD_MIN_ROTATION,
                animationSpec = tween(ROTATION_TIME)
            )
            rotationAnimation.animateTo(
                targetValue = 0f,
                animationSpec = tween(ROTATION_TIME)
            )
            isShinyEffectVisible = false
        } else {
            delay(
                INITIAL_ANIMATION_DELAY +
                        2 * EXTRA_SCALE_ANIMATION_TIME +
                        EXTRA_ALPHA_ANIMATION_TO_ZERO +
                        EXTRA_ALPHA_ANIMATION_TO_ONE +
                        NUMBER_OF_SHAKES * SINGLE_SHAKE_TIME + 4 * ROTATION_TIME
            )
        }
        onPlayerPresentationFinished()
    }

    Box(
        modifier = modifier
            .width(cardWidth + cardWidth * START_CARD_PADDING)
            .height(cardHeight + cardHeight * TOP_CARD_PADDING)
            .graphicsLayer {
                scaleX = scaleAnimation.value
                scaleY = scaleAnimation.value
                rotationY = rotationAnimation.value
                this.translationX = translationX
                this.translationY = translationY
                alpha = alphaAnimation.value
            }
    ) {
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(Color.Transparent, cardPrimary)
        )
        Card(
            modifier = Modifier.size(cardWidth, cardHeight)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(
                topStart = (cardWidth.value * CARD_CORNER_RADIUS).dp,
                topEnd = (cardWidth.value * CARD_CORNER_RADIUS).dp,
                bottomEnd = (cardWidth.value * CARD_CORNER_RADIUS).dp
            ),
        ) {
            val commonModifier = Modifier.fillMaxSize()
            val backgroundModifier = if (isShinyEffectVisible) {
                commonModifier.background(brush = brush)
            } else {
                commonModifier.background(color = cardSecondary)
            }
            Box(backgroundModifier)
        }

        Image(
            modifier = Modifier.fillMaxHeight().align(Alignment.TopEnd),
            painter = painterResource(playerInfoCardState.playerImage),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Text(
            modifier = Modifier.padding(
                top = cardHeight * NUMBER_TOP_PADDING,
                start = cardWidth * NUMBER_START_PADDING
            ),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            text = playerInfoCardState.number.toString(),
            lineHeight = (cardHeight.value * NUMBER_HEIGHT).sp,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = Color.White,
                    fontSize = (cardHeight.value * NUMBER_HEIGHT).sp,
                    drawStyle = Stroke(
                        width = cardWidth.value * PLAYER_NUMBER_STROKE,
                        join = StrokeJoin.Round
                    )
                )
            )
        )
        if (playerInfoCardState.isCaptain) {
            CaptainArmbandView(
                modifier = Modifier.align(Alignment.CenterStart),
                armbandWidth = cardHeight * COUNTRY_FLAG_HEIGHT * 1.75f,
                armbandHeight = cardHeight * COUNTRY_FLAG_HEIGHT,
                armbandColor = cardPrimary
            )
        } else {
            Image(
                modifier = Modifier.align(Alignment.CenterStart)
                    .height(cardHeight * COUNTRY_FLAG_HEIGHT),
                contentDescription = null,
                painter = painterResource(playerInfoCardState.countryFlag)
            )
        }
        Spacer(
            modifier = Modifier.fillMaxWidth()
                .height((cardHeight.value * BOTTOM_GRADIENT_HEIGHT).dp)
                .padding(start = (cardWidth.value * START_CARD_PADDING).dp)
                .drawBehind {
                    val path = Path()
                    path.lineTo(size.width, 0f)
                    path.lineTo(
                        size.width,
                        size.height - (cardWidth.value * CARD_CORNER_RADIUS).dp.toPx()
                    )
                    path.arcTo(
                        rect = Rect(
                            center = Offset(
                                size.width - (cardWidth.value * CARD_CORNER_RADIUS).dp.toPx(),
                                size.height - (cardWidth.value * CARD_CORNER_RADIUS).dp.toPx()
                            ),
                            radius = (cardWidth.value * CARD_CORNER_RADIUS).dp.toPx()
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = true
                    )
                    path.lineTo(0f, size.height)
                    path.lineTo(0f, 0f)
                    drawPath(path, brush = gradientBrush, style = Fill)
                }
                //.background(gradientBrush)
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
                .padding(
                    start = cardWidth * (START_CARD_PADDING + CARD_CONTENT_START_PADDING),
                    bottom = cardHeight * CARD_CONTENT_START_PADDING
                )
        ) {
            Text(
                text = playerInfoCardState.firstName,
                color = Color.White,
                lineHeight = (cardHeight.value * FIRST_NAME_SIZE).sp,
                fontSize = (cardHeight.value * FIRST_NAME_SIZE).sp
            )
            Text(
                text = playerInfoCardState.lastName,
                color = Color.White,
                fontSize = (cardHeight.value * LAST_NAME_SIZE).sp,
                lineHeight = (cardHeight.value * LAST_NAME_SIZE).sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun mapTo01(value: Float, minValue: Float, maxValue: Float): Float {
    return (value - minValue) / (maxValue - minValue)
}

enum class ShakeState {
    NOT_SHAKEN,
    SHAKE_TOP_LEFT,
    SHAKE_BOTTOM_LEFT,
    SHAKE_TOP_RIGHT,
    SHAKE_BOTTOM_RIGHT
}

const val COUNTRY_FLAG_HEIGHT = 0.1f
const val TOP_CARD_PADDING = 0.05f
const val START_CARD_PADDING = 0.04f
const val CARD_CONTENT_START_PADDING = 0.02f
const val NUMBER_HEIGHT = 0.25f
const val NUMBER_START_PADDING = 0.05f
const val NUMBER_TOP_PADDING = 0.03f
const val CARD_CORNER_RADIUS = 0.064f
const val PLAYER_NUMBER_STROKE = 0.024f
const val FIRST_NAME_SIZE = 0.04f
const val LAST_NAME_SIZE = 0.07f
const val BOTTOM_GRADIENT_HEIGHT = 0.2f
const val CARD_MIN_ROTATION = -45f
const val CARD_MAX_ROTATION = 45f

//shake animation timing
const val NUMBER_OF_SHAKES = 20
const val SINGLE_SHAKE_TIME = 25L
const val ROTATION_TIME = 1500
const val INITIAL_ANIMATION_DELAY = 250L
const val EXTRA_SCALE_ANIMATION_TIME = 250
const val EXTRA_ALPHA_ANIMATION_TO_ZERO = 100
const val EXTRA_ALPHA_ANIMATION_TO_ONE = 1
