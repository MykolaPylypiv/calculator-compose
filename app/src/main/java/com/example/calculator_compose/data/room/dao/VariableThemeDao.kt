package com.example.calculator_compose.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculator_compose.domain.model.VariableTheme

@Dao
interface VariableThemeDao {

    @Query("SELECT * FROM variableTheme")
    fun get(): VariableTheme

    @Query("DELETE FROM variableTheme")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variableTheme: VariableTheme)
}