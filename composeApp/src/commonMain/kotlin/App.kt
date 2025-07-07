package io.github.shalva97

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.shalva97.screens.search.SearchRoute
import io.github.shalva97.screens.search.searchScreen
import io.github.shalva97.screens.stats.PlayerStatsRoute
import io.github.shalva97.screens.stats.navigateToStatsScreen
import io.github.shalva97.screens.stats.statsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = SearchRoute) {
                searchScreen(
                    onPlayerStatsClicked = { name, background ->
                        navController.navigateToStatsScreen(PlayerStatsRoute(name, background))
                    }
                )
                statsScreen()
            }
        }
    }
}