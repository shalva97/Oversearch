package com.example.oversearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oversearch.data.PlayerRepository
import com.example.oversearch.domain.models.Player
import com.example.oversearch.domain.models.mock_data.mockPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    val players = MutableStateFlow(emptyList<Player>())
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        123
    }

    init {
        searchPlayer("shalva")
    }

    fun searchPlayer(name: String) = viewModelScope.launch(exceptionHandler) {
        players.emit(playerRepository.search(name))
    }
}