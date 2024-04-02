package com.ovidiucristurean.kmpsportcomposables

import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.AutomaticRevealState
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerCardColors
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.PlayerInfoCardState
import com.ovidiucristurean.kmpsportcomposables.composables.football.state.StartingLineupState
import kmpsportcomposables.shared.generated.resources.Res
import kmpsportcomposables.shared.generated.resources.al_dakhil
import kmpsportcomposables.shared.generated.resources.argentina_flag
import kmpsportcomposables.shared.generated.resources.belgium_flag
import kmpsportcomposables.shared.generated.resources.bernardo_silva
import kmpsportcomposables.shared.generated.resources.brazil_flag
import kmpsportcomposables.shared.generated.resources.connor_roberts
import kmpsportcomposables.shared.generated.resources.dara_oshea
import kmpsportcomposables.shared.generated.resources.ederson
import kmpsportcomposables.shared.generated.resources.england_flag
import kmpsportcomposables.shared.generated.resources.erling_haaland
import kmpsportcomposables.shared.generated.resources.germany_flag
import kmpsportcomposables.shared.generated.resources.ireland_flag
import kmpsportcomposables.shared.generated.resources.italy_flag
import kmpsportcomposables.shared.generated.resources.james_trafford
import kmpsportcomposables.shared.generated.resources.jordan_beyer
import kmpsportcomposables.shared.generated.resources.josh_cullen
import kmpsportcomposables.shared.generated.resources.julian_alvarez
import kmpsportcomposables.shared.generated.resources.kevin_de_bruyne
import kmpsportcomposables.shared.generated.resources.kyle_walker
import kmpsportcomposables.shared.generated.resources.luca_koleosho
import kmpsportcomposables.shared.generated.resources.lyle_foster
import kmpsportcomposables.shared.generated.resources.manuel_akanji
import kmpsportcomposables.shared.generated.resources.nathan_ake
import kmpsportcomposables.shared.generated.resources.netherlands_flag
import kmpsportcomposables.shared.generated.resources.norway_flag
import kmpsportcomposables.shared.generated.resources.phil_foden
import kmpsportcomposables.shared.generated.resources.portugal_flag
import kmpsportcomposables.shared.generated.resources.rico_lewis
import kmpsportcomposables.shared.generated.resources.rodri
import kmpsportcomposables.shared.generated.resources.sander_berge
import kmpsportcomposables.shared.generated.resources.south_africa_flag
import kmpsportcomposables.shared.generated.resources.spain_flag
import kmpsportcomposables.shared.generated.resources.switzerland_flag
import kmpsportcomposables.shared.generated.resources.vitinho
import kmpsportcomposables.shared.generated.resources.wales_flag
import kmpsportcomposables.shared.generated.resources.zeki_amdouni
import org.jetbrains.compose.resources.ExperimentalResourceApi

class FootballFieldScreenModel(
    private val teamStatus: TeamStatus
) : ScreenModel {

    @OptIn(ExperimentalResourceApi::class)
    val startingLineupState = when (teamStatus) {
        TeamStatus.HOME -> {
            StartingLineupState(
                playerLines = listOf(
                    listOf(
                        PlayerInfoCardState(
                            firstName = "James",
                            lastName = "Trafford",
                            number = 1,
                            countryFlag = Res.drawable.england_flag,
                            playerImage = Res.drawable.james_trafford,
                            isSpecialPlayer = true
                        )
                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "James",
                            lastName = "Al-Dakhil",
                            number = 28,
                            countryFlag = Res.drawable.belgium_flag,
                            playerImage = Res.drawable.al_dakhil
                        ),
                        PlayerInfoCardState(
                            firstName = "Jordan",
                            lastName = "Beyer",
                            number = 5,
                            countryFlag = Res.drawable.germany_flag,
                            playerImage = Res.drawable.jordan_beyer
                        ),
                        PlayerInfoCardState(
                            firstName = "Dara",
                            lastName = "O'Shea",
                            number = 2,
                            countryFlag = Res.drawable.ireland_flag,
                            playerImage = Res.drawable.dara_oshea
                        ),
                    ),
                    listOf(

                        PlayerInfoCardState(
                            firstName = "Connor",
                            lastName = "Roberts",
                            number = 14,
                            countryFlag = Res.drawable.wales_flag,
                            playerImage = Res.drawable.connor_roberts
                        ),
                        PlayerInfoCardState(
                            firstName = "Sander",
                            lastName = "Berge",
                            number = 16,
                            countryFlag = Res.drawable.norway_flag,
                            playerImage = Res.drawable.sander_berge
                        ),
                        PlayerInfoCardState(
                            firstName = "Josh",
                            lastName = "Cullen",
                            number = 24,
                            countryFlag = Res.drawable.ireland_flag,
                            playerImage = Res.drawable.josh_cullen,
                            isCaptain = true
                        ),
                        PlayerInfoCardState(
                            firstName = "",
                            lastName = "Vitinho",
                            number = 22,
                            countryFlag = Res.drawable.brazil_flag,
                            playerImage = Res.drawable.vitinho
                        )

                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "Zeki",
                            lastName = "Amdouni",
                            number = 25,
                            countryFlag = Res.drawable.switzerland_flag,
                            playerImage = Res.drawable.zeki_amdouni
                        )
                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "Luca",
                            lastName = "Koleosho",
                            number = 30,
                            countryFlag = Res.drawable.italy_flag,
                            playerImage = Res.drawable.luca_koleosho
                        ),
                        PlayerInfoCardState(
                            firstName = "Lyle",
                            lastName = "Foster",
                            number = 17,
                            countryFlag = Res.drawable.south_africa_flag,
                            playerImage = Res.drawable.lyle_foster
                        )
                    ),
                ),
                automaticRevealState = AutomaticRevealState.OnClick,
                playerCardColors = PlayerCardColors(
                    cardPrimary = Color(0xFF6C1D45),
                    cardSecondary = Color(0xFF892558),
                    cardTertiary = Color(0xFFa72d6b),
                )
            )
        }

        TeamStatus.AWAY -> {
            StartingLineupState(
                playerLines = listOf(
                    listOf(
                        PlayerInfoCardState(
                            firstName = "",
                            lastName = "Ederson",
                            number = 31,
                            countryFlag = Res.drawable.brazil_flag,
                            playerImage = Res.drawable.ederson
                        )
                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "Kyle",
                            lastName = "Walker",
                            number = 2,
                            countryFlag = Res.drawable.england_flag,
                            playerImage = Res.drawable.kyle_walker
                        ),
                        PlayerInfoCardState(
                            firstName = "Manuel",
                            lastName = "Akanji",
                            number = 25,
                            countryFlag = Res.drawable.switzerland_flag,
                            playerImage = Res.drawable.manuel_akanji
                        ),
                        PlayerInfoCardState(
                            firstName = "Nathan",
                            lastName = "Ake",
                            number = 6,
                            countryFlag = Res.drawable.netherlands_flag,
                            playerImage = Res.drawable.nathan_ake
                        ),
                        PlayerInfoCardState(
                            firstName = "Rico",
                            lastName = "Lewis",
                            number = 82,
                            countryFlag = Res.drawable.england_flag,
                            playerImage = Res.drawable.rico_lewis
                        )
                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "Kevin",
                            lastName = "de Bruyne",
                            number = 17,
                            countryFlag = Res.drawable.belgium_flag,
                            playerImage = Res.drawable.kevin_de_bruyne,
                            isCaptain = true
                        ),
                        PlayerInfoCardState(
                            firstName = "",
                            lastName = "Rodri",
                            number = 16,
                            countryFlag = Res.drawable.spain_flag,
                            playerImage = Res.drawable.rodri
                        ),
                        PlayerInfoCardState(
                            firstName = "Bernardo",
                            lastName = "Silva",
                            number = 20,
                            countryFlag = Res.drawable.portugal_flag,
                            playerImage = Res.drawable.bernardo_silva
                        )

                    ),
                    listOf(
                        PlayerInfoCardState(
                            firstName = "Julian",
                            lastName = "Alvarez",
                            number = 19,
                            countryFlag = Res.drawable.argentina_flag,
                            playerImage = Res.drawable.julian_alvarez
                        ),
                        PlayerInfoCardState(
                            firstName = "Erling",
                            lastName = "Haaland",
                            number = 9,
                            countryFlag = Res.drawable.norway_flag,
                            playerImage = Res.drawable.erling_haaland,
                            isSpecialPlayer = true
                        ),
                        PlayerInfoCardState(
                            firstName = "Phil",
                            lastName = "Foden",
                            number = 47,
                            countryFlag = Res.drawable.england_flag,
                            playerImage = Res.drawable.phil_foden
                        )
                    )
                ),
                automaticRevealState = AutomaticRevealState.Automatically(
                    delayBetweenLines = 1000
                ),
                playerCardColors = PlayerCardColors(
                    cardPrimary = Color(0xFF6CABDD),
                    cardSecondary = Color(0xFF7bb3e0),
                    cardTertiary = Color(0xFF89bce4),
                )
            )
        }
    }
}

enum class TeamStatus {
    HOME,
    AWAY
}
