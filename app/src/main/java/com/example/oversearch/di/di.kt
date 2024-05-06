package com.example.oversearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {
    @Provides
    fun getHttpClient(): HttpClient {
        return HttpClient(OkHttp) { install(ContentNegotiation) { json() } }
    }
}

@Module @InstallIn(SingletonComponent::class) abstract class RandomDeps {}
