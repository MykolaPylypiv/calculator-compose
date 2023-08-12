package com.example.calculator_compose.data.room

import androidx.room.*
import com.example.calculator_compose.domain.model.ColorTheme

@Dao
interface ColorThemeDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(color: ColorTheme)

    @Query("SELECT * FROM colorTheme")
    fun get(): List<ColorTheme>

    @Query("DELETE FROM colorTheme")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(color: ColorTheme)
}