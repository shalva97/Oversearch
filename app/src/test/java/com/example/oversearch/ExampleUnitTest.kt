package com.example.oversearch

import io.github.shalva97.overwatch_player_search_api.PlayerSearch
import kotlin.test.Test
import kotlinx.coroutines.runBlocking

class ExampleUnitTest {
    @Test
    fun asdf() = runBlocking {
        val s = PlayerSearch()

        println(s.searchForPlayer("bigman"))
    }
}
