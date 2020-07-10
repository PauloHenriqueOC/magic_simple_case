package com.magicsamplecase.application

import android.app.Application
import android.content.Context
import io.paperdb.Paper

class MyAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}