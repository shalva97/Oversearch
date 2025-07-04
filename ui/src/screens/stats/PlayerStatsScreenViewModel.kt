package ui.screens.stats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import data.PlayerRepository
import data.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlayerStatsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    private val playerInfoArg: PlayerStatsRoute = savedStateHandle.toRoute()

    val state = MutableStateFlow<PlayerStatsState>(PlayerStatsState.InitialState)

    init {
        viewModelScope.launch {
            val player = playerRepository.getPlayerProfile(playerInfoArg.tag)
            val playerWithBackground =
                player.copy(player = player.player.copy(title = playerInfoArg.background))
            state.emit(PlayerStatsState.Statistics(playerWithBackground))
        }
    }
}

sealed interface PlayerStatsState {
    data object InitialState : PlayerStatsState

    data object LoadingState : PlayerStatsState

    data object ErrorState : PlayerStatsState

    data class Statistics(val profile: Profile) : PlayerStatsState
}
