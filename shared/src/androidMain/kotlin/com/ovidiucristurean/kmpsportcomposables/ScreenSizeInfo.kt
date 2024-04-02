package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    val screenWidthPx = with(LocalDensity.current) {
        configuration.screenWidthDp.dp.toPx()
    }
    val screenHeightPx = with(LocalDensity.current) {
        configuration.screenHeightDp.dp.toPx()
    }
    return ScreenSize(
        widthPx = screenWidthPx,
        heightPx = screenHeightPx
    )
}
