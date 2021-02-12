package com.example.taskmo.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.taskmo.di.viewmodel.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    // This annotation provides a replacement of @Provides methods which simply return the injected parameter.
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}