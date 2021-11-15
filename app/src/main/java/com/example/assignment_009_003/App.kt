package com.example.assignment_009_003

import android.app.Application
import android.content.Context

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        appContext= applicationContext

    }

    companion object{
        var appContext: Context? = null
    }
}
