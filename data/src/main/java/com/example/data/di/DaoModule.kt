package com.example.data.di

import com.example.data.database.UniversitiesDatabase
import com.example.data.database.dao.UniversitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesUniversityDao(
        database: UniversitiesDatabase,
    ): UniversitiesDao = database.universitiesDao()
}
