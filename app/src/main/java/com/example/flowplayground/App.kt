package com.example.flowplayground

import android.app.Activity
import android.app.Application

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create()
    }

}

fun Activity.app(): App {
    return application as App
}