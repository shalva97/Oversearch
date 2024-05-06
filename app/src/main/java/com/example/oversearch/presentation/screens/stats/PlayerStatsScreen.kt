package com.example.oversearch.presentation.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.oversearch.domain.models.mock_data.player1
import com.example.oversearch.presentation.components.PlayerItem

@Composable
fun PlayerStatsScreen(
    viewModel: PlayerStatsScreenViewModel = hiltViewModel(),
    navController: NavHostController? = null,
) {
    Column(Modifier.fillMaxSize()) {
        PlayerItem(modifier = Modifier.height(60.dp), player = player1)
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Played 1 month 1 week 2 days",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview()
@Composable
private fun Preview() {
    PlayerStatsScreen()
}
