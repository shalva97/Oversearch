package data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::OverwatchPlayerSearchDataSource)
    factoryOf(::PlayerRepository)
    factory { Dispatchers.IO }
}