package com.example.calculator_compose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator_compose.data.room.dao.ColorThemeDao
import com.example.calculator_compose.data.room.dao.LanguageDao
import com.example.calculator_compose.data.room.dao.VariableThemeDao
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.Languages
import com.example.calculator_compose.domain.model.VariableTheme

@Database(
    entities = [ColorTheme::class, Languages::class, VariableTheme::class],
    version = 9,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun colorThemeDao(): ColorThemeDao
    abstract fun languageDao(): LanguageDao
    abstract fun variableThemeDao(): VariableThemeDao
}