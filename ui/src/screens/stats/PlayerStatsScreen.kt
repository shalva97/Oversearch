package ui.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import data.models.Player
import data.models.Profile
import org.jetbrains.compose.resources.painterResource

@Serializable
data class PlayerStatsRoute(val tag: String, val background: String?)

fun NavController.navigateToStatsScreen(playerStats: PlayerStatsRoute) {
    navigate(playerStats)
}

fun NavGraphBuilder.statsScreen() {
    composable<PlayerStatsRoute> {
        val viewModel: PlayerStatsScreenViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PlayerStatsScreen(state)
    }
}

@Composable
fun PlayerStatsScreen(
    state: PlayerStatsState,
) {
    Column(Modifier.fillMaxSize()) {
        when (state) {
            PlayerStatsState.ErrorState -> {
                Text(
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "No players found"
                )
            }

            PlayerStatsState.InitialState -> {
                Text("Loading...")
            }

            PlayerStatsState.LoadingState -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Text(
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Loading..."
                )
            }

            is PlayerStatsState.Statistics -> {
                Column {
                    Text(text = state.profile.player.username, style = MaterialTheme.typography.headlineMedium)
                    AsyncImage(
                        modifier = Modifier,
                        model = state.profile.player.backgroundImage,
                        contentDescription = null,
//                        placeholder = painterResource(id = R.drawable.img_sample_player_background)
                    )
                    AsyncImage(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(64.dp),
                        model = state.profile.player.image,
                        contentDescription = null,
//                    placeholder = painterResource(
//                        id = R.drawable.img_sample_player
//                    )
                    )
                    Text(text = state.profile.player.title ?: "")
                    Text(text = state.profile.endorsement.toString())
                }
            }
        }
    }
}

// @Preview
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
