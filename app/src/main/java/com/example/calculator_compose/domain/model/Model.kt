package com.example.calculator_compose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PresentationValues(val calculation: String, val action: String)

data class DomainAllValues(val calculation: String, val action: String, val history: String)

data class DomainValues(val numbers: MutableList<String>, val action: MutableList<String>)

@Entity(tableName = "language")
data class Languages(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "isEnglish") var isEnglish: Boolean = true,
)


