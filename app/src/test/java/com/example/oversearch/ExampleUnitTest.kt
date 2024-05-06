package com.example.oversearch

import io.github.shalva97.overwatch_player_search_api.PlayerSearch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ExampleUnitTest {
    @Test
    fun asdf() = runBlocking {
        val s = PlayerSearch()

        println(s.searchForPlayer("bigman"))
    }
}
