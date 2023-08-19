package com.example.calculator_compose.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculator_compose.domain.model.Languages

@Dao
interface LanguageDao {
    @Query("SELECT * FROM language")
    fun get(): Languages

    @Query("DELETE FROM language")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(languages: Languages)
}