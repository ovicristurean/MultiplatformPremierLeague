package com.ovidiucristurean.kmpsportcomposables.composables.football.state

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class PlayerInfoCardState @OptIn(ExperimentalResourceApi::class) constructor(
    val firstName: String,
    val lastName: String,
    val number: Int,
    val countryFlag: DrawableResource,
    val playerImage: DrawableResource,
    val isCaptain: Boolean = false,
    val isSpecialPlayer: Boolean = false
)
