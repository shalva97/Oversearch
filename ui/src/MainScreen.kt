package ui

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
import ui.screens.search.SearchRoute
import ui.screens.search.searchScreen
import ui.screens.stats.PlayerStatsRoute
import ui.screens.stats.navigateToStatsScreen
import ui.screens.stats.statsScreen

@Composable
fun MainScreen() {
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
