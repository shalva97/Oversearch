package com.example.oversearch.domain.models.mock_data

import com.example.oversearch.domain.models.Player

// Variant 1
val player1 = Player(
    username = "ShadowStriker",
    image = "https://example.com/images/shadowstriker.png",
    backgroundImage = "https://example.com/images/bg1.jpg",
    title = "Warrior"
)

// Variant 2
val player2 = Player(
    username = "DragonFury",
    image = "https://example.com/images/dragonfury.png",
    backgroundImage = "https://example.com/images/bg2.jpg",
    title = "Mage"
)

// Variant 3
val player3 = Player(
    username = "SilentAssassin",
    image = "https://example.com/images/silentassassin.png",
    backgroundImage = "https://example.com/images/bg3.jpg",
    title = "Rogue"
)

// Variant 4
val player4 = Player(
    username = "StormCaller",
    image = "https://example.com/images/stormcaller.png",
    backgroundImage = "https://example.com/images/bg4.jpg",
    title = "Shaman"
)

// Variant 5
val player5 = Player(
    username = "IronGuard",
    image = "https://example.com/images/ironguard.png",
    backgroundImage = "https://example.com/images/bg5.jpg",
    title = "Paladin"
)

val mockPlayers = listOf(player1, player2, player3, player4, player5)