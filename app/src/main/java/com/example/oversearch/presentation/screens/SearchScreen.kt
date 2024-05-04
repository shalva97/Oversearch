package com.example.oversearch.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.oversearch.presentation.components.PlayerItem

@Composable
fun SearchScreen(
    viewmodel: SearchScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by viewmodel.state.collectAsState()
    Column {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            reverseLayout = true,
            modifier = Modifier.weight(1f)
        ) {
            items(state.players) {
                PlayerItem(it)
            }
        }
        if (state.isLoading)
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        var searchText by remember { mutableStateOf("") }
        TextField(modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                if (state.isLoading.not())
                    viewmodel.searchPlayer(searchText)
            }),
            maxLines = 1,
            placeholder = { Text(text = "Search for a player") },
            value = searchText, onValueChange = { searchText = it })
    }
}
