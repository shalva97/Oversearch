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

@Composable
fun PlayerStatsScreen(
    state: PlayerStatsState,
    navController: NavHostController? = null,
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        when (state) {
            PlayerStatsState.ErrorState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "No players found"
                )
            }
            PlayerStatsState.InitialState -> {}
            PlayerStatsState.LoadingState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "Loading..."
                )
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
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
//    PlayerStatsScreen("shalva")
}
