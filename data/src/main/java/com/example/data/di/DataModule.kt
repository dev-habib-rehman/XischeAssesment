package com.example.data.di

import com.example.data.repository.UniversityRepository
import com.example.data.repository.UniversityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun provideUniversityRepository(
        universityRepositoryImpl: UniversityRepositoryImpl
    ): UniversityRepository
}