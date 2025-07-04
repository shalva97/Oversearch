package ui.di

import data.dataModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ui.screens.search.SearchScreenViewModel
import ui.screens.stats.PlayerStatsScreenViewModel

fun initKoin() {
    startKoin {
        modules(uiModule, dataModule)
    }
}

val uiModule = module {
    viewModelOf(::SearchScreenViewModel)
    viewModelOf(::PlayerStatsScreenViewModel)
}