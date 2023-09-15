package com.example.calculator_compose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calculator_compose.app.Strings

data class PresentationValues(val calculation: String, val action: String)

data class DomainAllValues(val calculation: String, val action: String, val history: String)

data class DomainValues(val numbers: MutableList<String>, val action: MutableList<String>)

@Entity(tableName = "language")
data class Languages(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "isEnglish") var isEnglish: Boolean = true,
)

@Entity(tableName = "variableTheme")
data class VariableTheme(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "theme") var theme: String = Strings.DARK,
)


