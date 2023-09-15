package com.example.calculator_compose.data.room.dao

import androidx.room.*
import com.example.calculator_compose.domain.model.ColorTheme

@Dao
interface ColorThemeDao {
    @Query("SELECT * FROM colorTheme")
    fun get(): ColorTheme

    @Query("DELETE FROM colorTheme")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(color: ColorTheme)
}