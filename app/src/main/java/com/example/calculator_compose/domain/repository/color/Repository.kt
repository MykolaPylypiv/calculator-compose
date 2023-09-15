package com.example.calculator_compose.domain.repository.color

import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.data.room.dao.ColorThemeDao
import javax.inject.Inject

interface Repository {
    fun colorTheme(): ColorTheme

    class Base @Inject constructor(private val colorThemeDao: ColorThemeDao) : Repository {
        override fun colorTheme() = colorThemeDao.get()
    }
}