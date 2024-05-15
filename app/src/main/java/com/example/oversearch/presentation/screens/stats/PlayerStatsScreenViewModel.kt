package com.example.oversearch.presentation.screens.stats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.oversearch.presentation.PLAYER_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerStatsScreenViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val userId: String = checkNotNull(savedStateHandle[PLAYER_TAG])

    val state = MutableStateFlow(userId)
}
