package com.example.oversearch.data.data

import com.example.oversearch.domain.models.Player
import io.github.shalva97.overwatch_player_search_api.models.OverwatchPlayer

fun OverwatchPlayer.toDomain(): Player {
  return Player(
      username = this.battleTag,
      image = this.portrait,
      backgroundImage = this.namecard,
      title = this.title)
}
