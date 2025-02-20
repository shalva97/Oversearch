package com.example.oversearch.data

import io.github.shalva97.overwatch_player_search_api.models.OverwatchPlayer
import io.github.shalva97.overwatch_player_search_api.models.profile.PlayerProfileStats

interface IOverwatchPlayerSearchDataSource {
    suspend fun search(text: String): List<OverwatchPlayer>
    suspend fun getPlayerProfile(name: String): PlayerProfileStats
}
