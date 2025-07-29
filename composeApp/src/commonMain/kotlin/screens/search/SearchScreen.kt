package io.github.shalva97.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import data.CrashlyticsManager
import data.models.Player
import io.github.shalva97.screens.search.components.PLAYER_ITEM_SIZE
import io.github.shalva97.screens.search.components.PlayerItem
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import oversearch.composeapp.generated.resources.Res
import oversearch.composeapp.generated.resources.search_for_a_player

@Serializable
object SearchRoute

fun NavGraphBuilder.searchScreen(
    onPlayerStatsClicked: (name: String, String?) -> Unit
) {
    composable<SearchRoute> {
        val viewmodel: SearchScreenViewModel = koinViewModel()
        val state by viewmodel.state.collectAsStateWithLifecycle()
        SearchScreen(
            state = state,
            onNavigateToPlayerStats = onPlayerStatsClicked,
            onPlayerSearch = viewmodel::searchPlayer,
        )
    }
}

@Composable
fun SearchScreen(
    state: SearchScreenState,
    onNavigateToPlayerStats: (name: String, String?) -> Unit = { _, _ -> },
    onPlayerSearch: (name: String) -> Unit = {}
) {
    Column(verticalArrangement = Arrangement.Bottom) {
        when (state) {
            SearchScreenState.ErrorState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "Something went wrong"
                )
            }

            SearchScreenState.InitialState -> {}
            SearchScreenState.LoadingState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "Loading..."
                )
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            is SearchScreenState.State -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(PLAYER_ITEM_SIZE.dp),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(
                        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                    )
                ) {
                    items(state.players) {
                        ElevatedCard(modifier = Modifier.padding(16.dp).height(60.dp)) {
                            PlayerItem(
                                player = it,
                                onNavigateToPlayerStats = onNavigateToPlayerStats
                            )
                        }
                    }
                }
            }

            SearchScreenState.NoPlayersFound -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "No players found"
                )
            }
        }
        var searchText by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.padding(8.dp)
                .navigationBarsPadding()
                .imePadding()
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                        if (state !is SearchScreenState.LoadingState) onPlayerSearch(searchText)
                    }
                ),
            maxLines = 1,
            placeholder = { Text(text = stringResource(Res.string.search_for_a_player)) },
            value = searchText,
            onValueChange = { searchText = it }
        )
    }
}

@Preview
@Composable
private fun PreviewLoading() {
    SearchScreen(SearchScreenState.LoadingState)
}

@Preview
@Composable
private fun PreviewNotLoading() {
    SearchScreen(SearchScreenState.InitialState)
}

@Preview
@Composable
private fun PreviewNoPlayersFound() {
    SearchScreen(SearchScreenState.NoPlayersFound)
}
