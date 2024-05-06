package com.example.oversearch.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oversearch.data.PlayerRepository
import com.example.oversearch.domain.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    val state = MutableStateFlow(SearchScreenState())
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        state.tryEmit(state.value.copy(isLoading = false))
    }

    fun searchPlayer(name: String) =
        viewModelScope.launch(exceptionHandler) {
        state.emit(state.value.copy(isLoading = true))
        val players = playerRepository.search(name)
        state.emit(state.value.copy(isLoading = false, players = players))
        }
}

data class SearchScreenState(
    val isLoading: Boolean = false,
    val players: List<Player> = emptyList(),
)
