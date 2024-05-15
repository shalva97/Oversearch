package com.example.oversearch.data

import com.example.oversearch.data.models.toDomain
import com.example.oversearch.di.Dispatcher
import com.example.oversearch.di.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PlayerRepository
@Inject
constructor(
    private val overwatchPlayerSearchDataSource: IOverwatchPlayerSearchDataSource,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun search(text: String) =
        withContext(ioDispatcher) {
            overwatchPlayerSearchDataSource.search(text).map { it.toDomain() }
        }

    suspend fun getPlayerProfile(name: String) = withContext(ioDispatcher){
        overwatchPlayerSearchDataSource.getPlayerProfile(name)
    }
    fun getLastSearchedPlayers(): List<String> {
        return listOf("shalva", "bigman") // TODO retrieve them from cache
    }
}
