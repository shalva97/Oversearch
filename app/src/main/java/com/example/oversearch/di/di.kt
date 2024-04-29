package com.example.oversearch.di

import com.example.oversearch.data.OverwatchResourcesDataSource
import com.example.oversearch.data.OverwatchResourcesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.buildJsonArray

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {
    @Provides
    fun getHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomDeps {
    @Binds
    abstract fun bindOverwatchResourcesDataSource(
        overwatchResourcesDataSource: OverwatchResourcesDataSourceImpl,
    ): OverwatchResourcesDataSource
}