package com.example.calculator_compose.domain.repository

import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.data.room.ColorThemeDao
import javax.inject.Inject

interface Repository {
    fun colorTheme(): List<ColorTheme>

    class Base @Inject constructor(private val colorThemeDao: ColorThemeDao) : Repository {
        override fun colorTheme() = colorThemeDao.get()
    }
}