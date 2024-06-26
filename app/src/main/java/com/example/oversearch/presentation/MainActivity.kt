package com.example.oversearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oversearch.presentation.screens.search.SearchScreen
import com.example.oversearch.presentation.screens.search.SearchScreenViewModel
import com.example.oversearch.presentation.screens.stats.PlayerStatsScreen
import com.example.oversearch.presentation.screens.stats.PlayerStatsScreenViewModel
import com.example.oversearch.presentation.theme.OversearchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OversearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = Search) {
            composable<Search> {
                val viewmodel: SearchScreenViewModel = hiltViewModel()
                val state by viewmodel.state.collectAsState()
                SearchScreen(
                    state = state,
                    onNavigateToPlayerStats = { name, background ->
                        navController.navigate(PlayerStats(name, background))
                    },
                    onPlayerSearch = viewmodel::searchPlayer,
                )
            }
            composable<PlayerStats> {
                val viewModel: PlayerStatsScreenViewModel = hiltViewModel()
                PlayerStatsScreen(viewModel.state.value)
            }
        }
    }
}

@Serializable
object Search

@Serializable
data class PlayerStats(val tag: String, val background: String?)
