package com.example.oversearch.domain

import com.example.oversearch.data.data.OverwatchPlayerDTO

interface IOverwatchPlayerSearchDataSource {
    suspend fun search(text: String): List<OverwatchPlayerDTO>
}
