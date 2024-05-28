package com.example.oversearch.presentation.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.oversearch.R
import com.example.oversearch.domain.models.Player
import com.example.oversearch.domain.models.Profile

@Composable
fun PlayerStatsScreen(
    state: PlayerStatsState,
) {
    Column(Modifier.fillMaxSize()) {
        when (state) {
            PlayerStatsState.ErrorState -> {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "No players found"
                )
            }

            PlayerStatsState.InitialState -> {}
            PlayerStatsState.LoadingState -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Loading..."
                )
            }

            is PlayerStatsState.Statistics -> {
                AsyncImage(
                    model = state.profile.player.backgroundImage,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.img_sample_player_background)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PlayerStatsScreen(
        PlayerStatsState.Statistics(
            Profile(
                player = Player(
                    username = "john_doe",
                    image = "https://example.com/john_doe.png",
                    backgroundImage = "https://example.com/background.png",
                    title = "Master Player"
                ),
                endorsement = 5,
                endorsementIcon = "https://example.com/icon.png",
                gamesWon = 30,
                gamesLost = 10,
                private = false
            )
        )
    )
}
