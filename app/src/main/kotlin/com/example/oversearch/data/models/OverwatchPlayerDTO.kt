package com.example.oversearch.data.models

import com.example.oversearch.domain.models.Player
import com.example.oversearch.domain.models.Profile
import io.github.shalva97.overwatch_player_search_api.domain.models.profile.PlayerProfileStats
import io.github.shalva97.overwatch_player_search_api.domain.models.search.OverwatchPlayer

fun OverwatchPlayer.toDomain(): Player {
    return Player(
        username = this.battleTag,
        image = this.portrait,
        backgroundImage = this.namecard,
        title = this.title
    )
}

fun PlayerProfileStats.toDomain(): Profile {
    return Profile(
        Player(name, icon, null, title),
        endorsement,
        endorsementIcon,
        gamesWon,
        gamesLost,
        private
    )
}
