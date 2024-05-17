package com.example.oversearch.presentation.screens.search

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oversearch.R
import com.example.oversearch.presentation.components.PlayerItem

@Composable
fun SearchScreen(
    state: SearchScreen,
    onNavigateToPlayerStats: (name: String, String?) -> Unit = { _, _ -> },
    onPlayerSearch: (name: String) -> Unit = {}
) {
    Column(Modifier.safeDrawingPadding(), verticalArrangement = Arrangement.Bottom) {
        when (state) {
            SearchScreen.ErrorState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "Something went wrong"
                )
            }
            SearchScreen.InitialState -> {}
            SearchScreen.LoadingState -> {
                Text(
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    text = "Loading..."
                )
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is SearchScreen.SearchScreenState -> {
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
            SearchScreen.NoPlayersFound -> {
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
                        if (state !is SearchScreen.LoadingState) onPlayerSearch(searchText)
                    }
                ),
            maxLines = 1,
            placeholder = { Text(text = stringResource(R.string.search_for_a_player)) },
            value = searchText,
            onValueChange = { searchText = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoading() {
    SearchScreen(SearchScreen.LoadingState)
}

@Preview
@Composable
private fun PreviewNotLoading() {
    SearchScreen(SearchScreen.InitialState)
}


@Preview
@Composable
private fun PreviewNoPlayersFound() {
    SearchScreen(SearchScreen.NoPlayersFound)
}
