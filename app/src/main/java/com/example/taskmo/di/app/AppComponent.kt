package com.example.taskmo.di.app

import com.example.taskmo.app.AppController
import com.example.taskmo.di.app.repository.AppCommonModule
import com.example.taskmo.domain.repository.AccountRepository
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class,AppCommonModule::class, NetworkModule::class,
        DatabaseModule::class]
)
@Singleton
interface AppComponent {
    fun inject(app: AppController)

    fun accountRepository(): AccountRepository

}