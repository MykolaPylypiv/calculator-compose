package com.example.calculator_compose.di

import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.domain.repository.color.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(database: AppDatabase): Repository =
        Repository.Base(database.colorThemeDao())
}