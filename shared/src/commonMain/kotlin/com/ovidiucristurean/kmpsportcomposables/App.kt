package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun App() {
    MaterialTheme {
        Navigator(HomeScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
