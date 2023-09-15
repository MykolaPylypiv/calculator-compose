package com.example.calculator_compose.domain.repository.variableTheme

import com.example.calculator_compose.data.room.dao.VariableThemeDao
import com.example.calculator_compose.domain.model.VariableTheme
import javax.inject.Inject

interface InsertVariableTheme {

    suspend fun insert(theme: VariableTheme)

    class Base @Inject constructor(private val variableThemeDao: VariableThemeDao) : InsertVariableTheme {
        override suspend fun insert(theme: VariableTheme) = variableThemeDao.insert(theme)
    }
}