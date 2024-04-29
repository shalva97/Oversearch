package com.example.oversearch.data.data

import com.example.oversearch.domain.models.Player
import kotlinx.serialization.Serializable

@Serializable
data class OverwatchPlayerDTO(
    val battleTag: String,
    val frame: String,
    val isPublic: Boolean,
    val lastUpdated: Long,
    val namecard: String,
    val portrait: String,
    val title: String,
    val url: String
)

fun OverwatchPlayerDTO.toDomain(): Player {
    return Player(
        username = this.battleTag,  // Assume username comes from battleTag
        image = this.portrait,  // Assume image comes from portrait
        backgroundImage = this.frame,  // Assume background image comes from frame
        title = this.title  // Title is the same as in DTO
    )
}