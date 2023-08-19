package com.example.calculator_compose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.Languages

@Database(entities = [ColorTheme::class, Languages::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun colorThemeDao(): ColorThemeDao
    abstract fun languageDao(): LanguageDao
}