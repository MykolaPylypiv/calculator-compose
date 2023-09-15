package com.example.calculator_compose.domain.repository.variableTheme

import com.example.calculator_compose.data.room.dao.VariableThemeDao
import javax.inject.Inject

interface DeleteVariableTheme {

    suspend fun deleteAll()

    class Base @Inject constructor(private val variableThemeDao: VariableThemeDao) : DeleteVariableTheme {
        override suspend fun deleteAll() = variableThemeDao.deleteAll()
    }
}