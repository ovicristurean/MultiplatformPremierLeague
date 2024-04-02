package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo


@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSize(): ScreenSize {
    val containerSize = LocalWindowInfo.current.containerSize

    return ScreenSize(
        widthPx = containerSize.width.toFloat(),
        heightPx = containerSize.height.toFloat()
    )
}
