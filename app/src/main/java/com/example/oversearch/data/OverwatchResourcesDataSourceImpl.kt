package com.example.oversearch.data

import android.content.res.Resources
import com.example.oversearch.R
import javax.inject.Inject

interface OverwatchResourcesDataSource {
    fun readRawJson(): String
}

class OverwatchResourcesDataSourceImpl @Inject constructor() : OverwatchResourcesDataSource {
    override fun readRawJson(): String {
        return Resources.getSystem().openRawResource(R.raw.ow_data).bufferedReader().readText()
    }
}