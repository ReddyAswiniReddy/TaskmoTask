package com.example.taskmo.di.app.repository

import com.example.taskmo.data.AppDatabase
import com.example.taskmo.data.WebApi
import com.example.taskmo.data.account.AccountDao
import com.example.taskmo.data.account.AccountDataRepository
import com.example.taskmo.data.account.UserFavoriteDao
import com.example.taskmo.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppCommonModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): WebApi {
        return retrofit.create(WebApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase):AccountDao {
        return database.accountDao()
    }

    @Singleton
    @Provides
    fun provideUserFavDao(database: AppDatabase):UserFavoriteDao {
        return database.userFavoriteDao()
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: WebApi,
        dao: AccountDao,
        userFavoriteDao: UserFavoriteDao
    ): AccountRepository {
        return AccountDataRepository(
            api,
            dao,
            userFavoriteDao
        )
    }
}