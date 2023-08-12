package com.example.calculator_compose.di

import android.content.Context
import androidx.room.Room
import com.example.calculator_compose.app.Strings.DATABASE_NAME
import com.example.calculator_compose.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideColorDao(db: AppDatabase) = db.colorThemeDao()

    @Provides
    fun provideDispatchers(): CoroutineContext = Dispatchers.IO
}