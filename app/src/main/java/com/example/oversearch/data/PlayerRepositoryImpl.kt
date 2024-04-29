package com.example.oversearch.data

import com.example.oversearch.di.Dispatcher
import com.example.oversearch.di.Dispatchers
import com.example.oversearch.domain.models.Player
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val overwatchPlayerSearchDataSource: OverwatchPlayerSearchDataSource,
    private val owData: OverwatchResourcesDataSource,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun search(text: String) = withContext(ioDispatcher) {
        val parsedOWData = Json.parseToJsonElement(owData.readRawJson())
        overwatchPlayerSearchDataSource.search(text)
            .map {
                Player(
                    username = it.battleTag,
                    image = it.portrait,
                    backgroundImage = it.frame,
                    title = it.title
                )
            }
    }

    fun getLastSearchedPlayers(): List<String> {
        return listOf("shalva", "bigman")
    }
}
