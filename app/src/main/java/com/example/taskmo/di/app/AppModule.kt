package com.example.taskmo.di.app

import android.content.Context
import com.example.taskmo.app.AppController
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: AppController) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): AppController {
        return application
    }
}