package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovidiucristurean.kmpsportcomposables.composables.AutoResizedText
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerCardState

@Composable
fun PlayerNotRevealedCard(
    modifier: Modifier,
    playerCardState: PlayerCardState,
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    cardPrimary: Color = Color(0xFF6C1D45),
    cardSecondary: Color = Color(0xFF892558),
    cardTertiary: Color = Color(0xFFa72d6b),
    textColor: Color = Color.White,
) {
    val cardRadius by remember {
        mutableStateOf(width * 0.06f)
    }

    Column(
        modifier = modifier
            .size(width, height)
            .clip(
                RoundedCornerShape(
                    topStart = cardRadius,
                    topEnd = cardRadius,
                    bottomStart = cardRadius,
                    bottomEnd = 0.dp
                )
            )
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(4 / 5f)
                .background(cardSecondary)
                .drawBehind {
                    val path = Path()
                    path.moveTo(cardRadius.toPx(), 0f)
                    path.lineTo(size.width / 4f, size.height / 3f)
                    path.lineTo(
                        cardRadius.toPx(),
                        size.height / 3f - cardRadius.toPx()
                    )
                    path.lineTo(size.width / 4f, size.height)
                    path.lineTo(0f, size.height)
                    path.lineTo(0f, cardRadius.toPx())
                    path.arcTo(
                        rect = Rect(
                            center = Offset(cardRadius.toPx(), cardRadius.toPx()),
                            radius = cardRadius.toPx()
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = true
                    )
                    drawPath(path, color = cardTertiary, style = Fill)
                },
            contentAlignment = Alignment.Center
        ) {
            AutoResizedText(
                text = playerCardState.number.toString(),
                color = textColor,
                style = TextStyle(
                    fontSize = (height.value * 0.5).sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        Box(modifier = Modifier.fillMaxWidth()
            .weight(1f)
            .graphicsLayer {
                shape = RoundedCornerShape(bottomStart = cardRadius.toPx())
                clip = true
            }
            .background(cardPrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = playerCardState.name,
                color = textColor,
                style = TextStyle(
                    fontSize = (height.value * 0.5 * 0.2).sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
    }
}
