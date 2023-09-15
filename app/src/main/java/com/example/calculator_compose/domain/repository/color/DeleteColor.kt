package com.example.calculator_compose.domain.repository.color

import com.example.calculator_compose.data.room.dao.ColorThemeDao
import javax.inject.Inject

interface DeleteColor {

    suspend fun deleteAll()

    class Base @Inject constructor(private val colorThemeDao: ColorThemeDao) : DeleteColor {
        override suspend fun deleteAll() = colorThemeDao.deleteAll()
    }
}