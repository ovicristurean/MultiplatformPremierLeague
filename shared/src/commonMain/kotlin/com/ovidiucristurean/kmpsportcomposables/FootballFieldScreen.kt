package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.ovidiucristurean.kmpsportcomposables.composables.football.FootballFieldView
import com.ovidiucristurean.kmpsportcomposables.composables.football.view.LineupView
import kmpsportcomposables.shared.generated.resources.Res
import kmpsportcomposables.shared.generated.resources.etihad
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.parameter.parametersOf

class FootballFieldScreen(
    private val teamStatus: TeamStatus
) : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<FootballFieldScreenModel>(
            parameters = {
                parametersOf(teamStatus)
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 2f),
                painter = painterResource(Res.drawable.etihad),
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )
            FootballFieldView(
                modifier = Modifier.fillMaxSize()
                    .graphicsLayer {
                        rotationX = 5f
                    }
            )
            LineupView(
                lineup = viewModel.startingLineupState
            )
        }
    }
}
