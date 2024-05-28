package com.example.oversearch.domain.models

import io.github.shalva97.overwatch_player_search_api.models.profile.Rating

data class Profile(
    val player: Player,
    val endorsement: Int,
    val endorsementIcon: String,
//    val ratings: List<Rating>, // TODO
    val gamesWon: Int,
    val gamesLost: Int,
    val private: Boolean,
)

