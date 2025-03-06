package com.example.oversearch.data

import io.github.shalva97.overwatch_player_search_api.domain.models.profile.PlayerProfileStats
import io.github.shalva97.overwatch_player_search_api.domain.models.search.OverwatchPlayer

interface IOverwatchPlayerSearchDataSource {
    suspend fun search(text: String): List<OverwatchPlayer>
    suspend fun getPlayerProfile(name: String): PlayerProfileStats
}
