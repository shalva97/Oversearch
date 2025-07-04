package ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import ui.screens.search.components.PlayerItem

@Serializable
object SearchRoute

fun NavGraphBuilder.searchScreen(
    onPlayerStatsClicked: (name: String, String?) -> Unit
) {
    composable<SearchRoute> {
        val viewmodel: SearchScreenViewModel = koinViewModel()
        val state by viewmodel.state.collectAsState()
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
    Column(Modifier.safeDrawingPadding(), verticalArrangement = Arrangement.Bottom) {
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
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    reverseLayout = true,
                    modifier = Modifier.weight(1f)
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
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                        if (state !is SearchScreenState.LoadingState) onPlayerSearch(searchText)
                    }
                ),
            maxLines = 1,
//            placeholder = { Text(text = stringResource(Res.string.search_for_a_player)) }, TODO
            value = searchText,
            onValueChange = { searchText = it }
        )
    }
}

//@Preview(showBackground = true)
@Composable
private fun PreviewLoading() {
    SearchScreen(SearchScreenState.LoadingState)
}

//@Preview
@Composable
private fun PreviewNotLoading() {
    SearchScreen(SearchScreenState.InitialState)
}

//@Preview
@Composable
private fun PreviewNoPlayersFound() {
    SearchScreen(SearchScreenState.NoPlayersFound)
}
