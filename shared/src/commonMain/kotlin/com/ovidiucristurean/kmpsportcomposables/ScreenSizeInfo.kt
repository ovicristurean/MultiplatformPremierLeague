package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.runtime.Composable

@Composable
expect fun getScreenSize(): ScreenSize

data class ScreenSize(
    val widthPx: Float,
    val heightPx: Float
)
