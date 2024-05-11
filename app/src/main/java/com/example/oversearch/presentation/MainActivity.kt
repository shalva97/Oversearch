package com.example.oversearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oversearch.presentation.screens.search.SearchScreen
import com.example.oversearch.presentation.screens.stats.PlayerStatsScreen
import com.example.oversearch.presentation.theme.OversearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
        setContent {
            OversearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) { SearchScreen(navController = navController) }
        composable(PLAYER_STATS) { PlayerStatsScreen(navController = navController) }
    }
}

const val HOME = "HOME"
const val PLAYER_STATS = "PLAYER_STATS"
