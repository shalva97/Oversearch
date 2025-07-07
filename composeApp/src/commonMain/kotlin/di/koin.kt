package io.github.shalva97.di

import data.dataModule
import io.github.shalva97.screens.search.SearchScreenViewModel
import io.github.shalva97.screens.stats.PlayerStatsScreenViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun initOversearchApp() {
    startKoin {
        modules(uiModule, dataModule)
    }
}

val uiModule = module {
    viewModelOf(::SearchScreenViewModel)
    viewModelOf(::PlayerStatsScreenViewModel)
}
