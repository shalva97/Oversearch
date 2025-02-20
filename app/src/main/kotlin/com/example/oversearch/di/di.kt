package com.example.oversearch.di

import com.example.oversearch.data.IOverwatchPlayerSearchDataSource
import com.example.oversearch.data.OverwatchPlayerSearchDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {
    @Provides
    fun getHttpClient(): HttpClient {
        return HttpClient { install(ContentNegotiation) { json() } }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomDeps {
    @Binds
    abstract fun bindStuff(
        source: OverwatchPlayerSearchDataSource
    ): IOverwatchPlayerSearchDataSource
}
