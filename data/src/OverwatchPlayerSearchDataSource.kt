package data

import io.github.shalva97.overwatch_player_search_api.PlayerSearch
import io.github.shalva97.overwatch_player_search_api.domain.models.profile.PlayerProfileStats
import io.github.shalva97.overwatch_player_search_api.domain.models.search.OverwatchPlayer

class OverwatchPlayerSearchDataSource {

    private val playerSearch = PlayerSearch()

    suspend fun search(text: String): List<OverwatchPlayer> {
        return playerSearch.searchForPlayer(text)
    }

    suspend fun getPlayerProfile(name: String): PlayerProfileStats {
       return playerSearch.getPlayerProfileForPC(name)
    }
}