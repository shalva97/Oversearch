package com.example.oversearch.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oversearch.data.PlayerRepository
import com.example.oversearch.domain.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    val state = MutableStateFlow<SearchScreen>(SearchScreen.InitialState)
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        state.tryEmit(SearchScreen.ErrorState)
    }

    fun searchPlayer(name: String) =
        viewModelScope.launch(exceptionHandler) {
            setLoading()
            val players = playerRepository.search(name)
            if (players.isNotEmpty()) showResult(players) else showNoPlayersFound()
        }

    private suspend fun setLoading() {
        state.emit(SearchScreen.LoadingState)
    }

    private suspend fun showResult(players: List<Player>) {
        state.emit(SearchScreen.SearchScreenState(players))
    }

    private suspend fun showNoPlayersFound() {
        state.emit(SearchScreen.NoPlayersFound)
    }
}

sealed interface SearchScreen {
    data class SearchScreenState(
        val players: List<Player> = emptyList(),
    ) : SearchScreen

    data object InitialState : SearchScreen

    data object LoadingState : SearchScreen

    data object ErrorState : SearchScreen

    data object NoPlayersFound : SearchScreen
}
