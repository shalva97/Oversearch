package com.example.oversearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.oversearch.domain.models.mock_data.player1
import com.example.oversearch.presentation.components.PlayerItem
import com.example.oversearch.presentation.theme.OversearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel by viewModels<MainActivityViewModel>()
            val players by viewmodel.players.collectAsState()
            OversearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            reverseLayout = true,
                            modifier = Modifier.weight(1f)
                        ) {
                            items(players) {
                                PlayerItem(it)
                            }
                        }
                        TextField(modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Search for a player") },
                            value = "", onValueChange = {})
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OversearchTheme {
        PlayerItem(player1)
    }
}
