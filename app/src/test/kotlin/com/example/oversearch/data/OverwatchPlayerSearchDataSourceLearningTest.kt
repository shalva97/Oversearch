package com.example.oversearch.data

import com.example.oversearch.httpClient
import kotlin.test.Test
import kotlinx.coroutines.runBlocking

class OverwatchPlayerSearchDataSourceLearningTest {

    @Test
    fun getPlayerProfile() = runBlocking {
        val source = OverwatchPlayerSearchDataSource(httpClient)

//        println(source.search("shalva"))
        println(source.getPlayerProfile("shalva-21962"))
    }
}
