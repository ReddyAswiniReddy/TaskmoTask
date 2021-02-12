package com.example.taskmo.app

import android.app.Application
import com.example.taskmo.di.app.AppComponent
import com.example.taskmo.di.app.AppModule
import com.example.taskmo.di.app.DaggerAppComponent

class AppController : Application() {

    companion object {
        lateinit var instance: AppController
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }

}