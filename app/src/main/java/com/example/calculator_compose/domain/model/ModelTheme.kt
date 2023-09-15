package com.example.calculator_compose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colorTheme")
data class ColorTheme(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "primaryText") var primaryText: Long = 0xFF000000,
    @ColumnInfo(name = "secondaryText") var secondaryText: Long = 0xFFfe5e00,
    @ColumnInfo(name = "tertiaryText") var tertiaryText: Long = 0xFF0591b4,
    @ColumnInfo(name = "historyColor") var additionalTextColor: Long = 0xFF808080,
    @ColumnInfo(name = "primaryButton") var primaryButton: Long = 0xFFFFFFFF,
    @ColumnInfo(name = "primaryBackground") var primaryBackground: Long = 0xFFFFFFFF,
)

data class DefaultColorHex(
    val black: Long = 0xFF000000,
    val white: Long = 0xFFFFFFFF,
    val lightGray: Long = 0xFF333333,
    val gray: Long = 0xFF808080,
    val darkGray: Long = 0xFF797979
)

