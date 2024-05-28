package com.example.oversearch.presentation.screens.stats

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.oversearch.data.PlayerRepository
import com.example.oversearch.domain.models.Profile
import com.example.oversearch.presentation.MainActivity
import com.example.oversearch.presentation.PlayerStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerStatsScreenViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    private val playerInfoArg: PlayerStats = savedStateHandle.toRoute()

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
