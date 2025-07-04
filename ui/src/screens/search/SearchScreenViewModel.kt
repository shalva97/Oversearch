package ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PlayerRepository
import data.models.Player
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    val state = MutableStateFlow<SearchScreenState>(SearchScreenState.InitialState)
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        state.tryEmit(SearchScreenState.ErrorState)
    }

    fun searchPlayer(name: String) =
        viewModelScope.launch(exceptionHandler) {
            setLoading()
            val players = playerRepository.search(name)
            if (players.isNotEmpty()) showResult(players) else showNoPlayersFound()
        }

    private suspend fun setLoading() {
        state.emit(SearchScreenState.LoadingState)
    }

    private suspend fun showResult(players: List<Player>) {
        state.emit(SearchScreenState.State(players))
    }

    private suspend fun showNoPlayersFound() {
        state.emit(SearchScreenState.NoPlayersFound)
    }
}

sealed interface SearchScreenState {
    data class State(
        val players: List<Player> = emptyList(),
    ) : SearchScreenState

    data object InitialState : SearchScreenState

    data object LoadingState : SearchScreenState

    data object ErrorState : SearchScreenState

    data object NoPlayersFound : SearchScreenState
}
