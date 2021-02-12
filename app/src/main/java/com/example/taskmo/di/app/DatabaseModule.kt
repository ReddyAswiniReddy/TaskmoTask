package com.example.taskmo.di.app

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmo.app.AppController
import com.example.taskmo.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(
            AppController.instance, AppDatabase::class.java,
            "taskmo"
        )
            .allowMainThreadQueries()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }
}