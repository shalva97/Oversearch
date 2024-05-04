package com.example.oversearch.data

import com.example.oversearch.data.data.OverwatchPlayerDTO
import com.example.oversearch.domain.IOverwatchPlayerSearchDataSource

class FakeOverwatchPlayerSearchDataSourceTest : IOverwatchPlayerSearchDataSource {
    override suspend fun search(text: String): List<OverwatchPlayerDTO> {
        return listOf(
            OverwatchPlayerDTO(
                battleTag = "shalva#21962",
                frame = "0x0250000000000918",
                isPublic = true,
                lastUpdated = 1714150720L,
                namecard = "0x0250000000006A8D",
                portrait = "0x0250000000006E0A",
                title = "0x0250000000006B2E",
                url = "e156ad86a570d6ffb8a921a2%7C44a3944e9291875ca4bff9bad8d45767"
            )
        )
    }
}

val playerSearchText = """[
  {
    "battleTag": "shalva#21962",
    "frame": "0x0250000000000918",
    "isPublic": true,
    "lastUpdated": 1714150720,
    "namecard": "0x0250000000006A8D",
    "portrait": "0x0250000000006E0A",
    "title": "0x0250000000006B2E",
    "url": "e156ad86a570d6ffb8a921a2%7C44a3944e9291875ca4bff9bad8d45767"
  }
]
"""
