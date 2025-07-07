package data

import io.github.shalva97.BuildKonfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::OverwatchPlayerSearchDataSource)
    factoryOf(::PlayerRepository)
    factoryOf(::CrashlyticsManager)
    factory { Dispatchers.IO }
    BuildKonfig
}
