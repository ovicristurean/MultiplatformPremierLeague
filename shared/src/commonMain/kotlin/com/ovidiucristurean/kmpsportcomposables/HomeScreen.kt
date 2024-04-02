package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerInfoCardState
import com.ovidiucristurean.kmpsportcomposables.composables.football.view.CaptainArmbandView
import com.ovidiucristurean.kmpsportcomposables.composables.football.view.PlayerPresentationCard
import kmpsportcomposables.shared.generated.resources.Res
import kmpsportcomposables.shared.generated.resources.england_flag
import kmpsportcomposables.shared.generated.resources.james_trafford
import org.jetbrains.compose.resources.ExperimentalResourceApi

class HomeScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigator.push(FootballFieldScreen(TeamStatus.HOME))
                }
            ) {
                Text("Home team lineup")
            }

            Button(
                onClick = {
                    navigator.push(FootballFieldScreen(TeamStatus.AWAY))
                }
            ) {
                Text("Away team lineup")
            }

            Spacer(Modifier.height(8.dp))

            CaptainArmbandView(
                armbandWidth = 150.dp,
                armbandHeight = 75.dp
            )

            Spacer(Modifier.height(8.dp))

            PlayerPresentationCard(
                cardWidth = 100.dp,
                cardHeight = 150.dp,
                playerInfoCardState = PlayerInfoCardState(
                    firstName = "James",
                    lastName = "Trafford",
                    number = 1,
                    countryFlag = Res.drawable.england_flag,
                    playerImage = Res.drawable.james_trafford
                ),
                cardPrimary = Color(0xFF6C1D45),
                cardSecondary = Color(0xFF892558),
                onPlayerPresentationFinished = {}
            )


        }
    }
}
