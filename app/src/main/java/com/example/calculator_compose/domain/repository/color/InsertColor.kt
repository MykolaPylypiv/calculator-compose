package com.example.calculator_compose.domain.repository.color

import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.data.room.dao.ColorThemeDao
import javax.inject.Inject

interface InsertColor {

    suspend fun insert(color: ColorTheme)

    class Base @Inject constructor(private val colorThemeDao: ColorThemeDao) : InsertColor {
        override suspend fun insert(color: ColorTheme) = colorThemeDao.insert(color)
    }
}