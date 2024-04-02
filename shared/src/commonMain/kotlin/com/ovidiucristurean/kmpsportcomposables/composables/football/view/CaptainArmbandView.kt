package com.ovidiucristurean.kmpsportcomposables.composables.football.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun CaptainArmbandView(
    armbandWidth: Dp,
    armbandHeight: Dp,
    modifier: Modifier = Modifier,
    armbandColor: Color = MaterialTheme.colorScheme.primary,
    armbandText: String = "Captain",
    numberOfIterations: Int = 3
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val armbandWidthPx = with(LocalDensity.current) {
        armbandWidth.toPx()
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            repeat(numberOfIterations) {
                listState.animateScrollBy(
                    armbandWidthPx,
                    animationSpec = tween(
                        durationMillis = 4000,
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    Column(
        modifier = modifier.size(width = armbandWidth, height = armbandHeight),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(armbandHeight / 6)
                .background(armbandColor)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White),
            state = listState,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(numberOfIterations) { item ->
                Text(
                    modifier = Modifier.width(armbandWidth),
                    text = armbandText,
                    textAlign = TextAlign.Center,
                    color = armbandColor,
                    style = TextStyle(
                        fontSize = (armbandHeight.value * 0.4).sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(armbandHeight / 6)
                .background(armbandColor)
        )
    }
}
