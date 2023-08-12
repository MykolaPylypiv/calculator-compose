package com.example.calculator_compose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator_compose.domain.model.ColorTheme

@Database(entities = [ColorTheme::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun colorThemeDao(): ColorThemeDao
}