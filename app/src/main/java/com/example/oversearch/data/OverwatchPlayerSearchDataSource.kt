package com.example.oversearch.data

import com.example.oversearch.data.data.OverwatchPlayerDTO
import com.example.oversearch.domain.IOverwatchPlayerSearchDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class OverwatchPlayerSearchDataSource @Inject constructor(
    private val client: HttpClient,
) : IOverwatchPlayerSearchDataSource {

    override suspend fun search(text: String): List<OverwatchPlayerDTO> {
        return client.get("https://overwatch.blizzard.com/en-us/search/account-by-name/shalva/")
            .body()
    }
}
