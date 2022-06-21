package com.example.sixth_home_task.di

import com.example.sixth_home_task.di.module.AppModule
import com.example.sixth_home_task.di.module.DataModule
import com.example.sixth_home_task.di.module.DomainModule
import com.example.sixth_home_task.presentation.general.MapsActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
@Singleton
interface AppComponent {
    fun inject(mapsActivity: MapsActivity)
}
