package com.example.oversearch.data

import android.content.Context
import com.example.oversearch.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface OverwatchResourcesDataSource {
    fun readRawJson(): String
}

class OverwatchResourcesDataSourceImpl @Inject constructor(
    @ApplicationContext private val app: Context,
) : OverwatchResourcesDataSource {
    override fun readRawJson(): String {
        return app.resources.openRawResource(R.raw.ow_data).bufferedReader().readText()
    }
}
