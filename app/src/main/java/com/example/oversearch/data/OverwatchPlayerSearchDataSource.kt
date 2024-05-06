package com.example.oversearch.data

import io.github.shalva97.overwatch_player_search_api.PlayerSearch
import io.github.shalva97.overwatch_player_search_api.models.OverwatchPlayer
import io.ktor.client.HttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OverwatchPlayerSearchDataSource
@Inject
constructor(
    private val client: HttpClient,
) : IOverwatchPlayerSearchDataSource {

    private val playerSearch = PlayerSearch()

    override suspend fun search(text: String): List<OverwatchPlayer> {
        return playerSearch.searchForPlayer(text)
    }
}
