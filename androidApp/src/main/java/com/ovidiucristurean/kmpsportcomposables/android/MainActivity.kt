package com.ovidiucristurean.kmpsportcomposables.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.ovidiucristurean.kmpsportcomposables.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalConfiguration.current.screenHeightDp
            App()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {

    }
}
