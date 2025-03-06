package com.example.oversearch.data

import io.github.shalva97.overwatch_player_search_api.PlayerSearch
import io.github.shalva97.overwatch_player_search_api.domain.models.profile.PlayerProfileStats
import io.github.shalva97.overwatch_player_search_api.domain.models.search.OverwatchPlayer
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OverwatchPlayerSearchDataSource
@Inject
constructor() : IOverwatchPlayerSearchDataSource {

    private val playerSearch = PlayerSearch()

    override suspend fun search(text: String): List<OverwatchPlayer> {
        return playerSearch.searchForPlayer(text)
    }

    override suspend fun getPlayerProfile(name: String): PlayerProfileStats {
       return playerSearch.getPlayerProfileForPC(name)
    }
}
