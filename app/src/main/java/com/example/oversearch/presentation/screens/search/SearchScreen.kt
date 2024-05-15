package com.example.oversearch.presentation.screens.search

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
import androidx.compose.ui.unit.dp
import com.example.oversearch.R
import com.example.oversearch.presentation.components.PlayerItem

@Composable
fun SearchScreen(
    state: SearchScreenState,
    onNavigateToPlayerStats: (name: String) -> Unit = {},
    onPlayerSearch: (name: String) -> Unit = {}
) {
    Column(Modifier.safeDrawingPadding()) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            reverseLayout = true,
            modifier = Modifier.weight(1f)
        ) {
            items(state.players) {
                ElevatedCard(modifier = Modifier.padding(16.dp).height(60.dp)) {
                    PlayerItem(player = it, onNavigateToPlayerStats = onNavigateToPlayerStats)
                }
            }
        }
        if (state.isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        var searchText by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions =
                KeyboardActions(
                    onSearch = { if (state.isLoading.not()) onPlayerSearch(searchText) }
                ),
            maxLines = 1,
            placeholder = { Text(text = stringResource(R.string.search_for_a_player)) },
            value = searchText,
            onValueChange = { searchText = it }
        )
    }
}
