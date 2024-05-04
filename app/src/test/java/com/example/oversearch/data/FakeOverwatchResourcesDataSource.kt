package com.example.oversearch.data

import java.io.File


class FakeOverwatchResourcesDataSource : OverwatchResourcesDataSource {
    override fun readRawJson(): String {
        return File("src/main/res/raw/ow_data.json").readText()
    }
}
