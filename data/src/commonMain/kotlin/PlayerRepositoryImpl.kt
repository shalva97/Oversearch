package data

import data.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PlayerRepository(
    private val overwatchPlayerSearchDataSource: OverwatchPlayerSearchDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun search(text: String) = withContext(ioDispatcher) {
        overwatchPlayerSearchDataSource
            .search(text)
            .map { it.toDomain() }
    }

    suspend fun getPlayerProfile(name: String) = withContext(ioDispatcher) {
        overwatchPlayerSearchDataSource
            .getPlayerProfile(name)
            .toDomain()
    }

    fun getLastSearchedPlayers(): List<String> {
        return listOf("shalva", "bigman") // TODO retrieve them from cache
    }
}
