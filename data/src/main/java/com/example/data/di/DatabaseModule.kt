package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.UniversitiesDatabase
import com.example.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): UniversitiesDatabase = Room.databaseBuilder(
        context,
        UniversitiesDatabase::class.java,
        Constants.DB_NAME,
    ).build()
}
