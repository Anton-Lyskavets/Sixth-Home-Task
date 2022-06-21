package com.example.sixth_home_task.presentation.app

import android.app.Application
import com.example.sixth_home_task.di.AppComponent
import com.example.sixth_home_task.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}