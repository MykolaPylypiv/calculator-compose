package com.example.calculator_compose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colorTheme")
data class ColorTheme(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "primaryText") var primaryText: Long = 0x000000,
    @ColumnInfo(name = "secondaryText") var secondaryText: Long = 0xFFfe5e00,
    @ColumnInfo(name = "primaryButton") var primaryButton: Long = 0xFFFFF,
    @ColumnInfo(name = "secondaryButton") var secondaryButton: Long = 0xFF1c1c1c,
    @ColumnInfo(name = "tertiaryText") var tertiaryText: Long = 0xFF0591b4,
    @ColumnInfo(name = "historyColor") var historyColor: Long = 0x000000,
    @ColumnInfo(name = "primaryBackground") var primaryBackground: Long = 0xFFFFF,
)

val LightColorTheme = ColorTheme(
    uid = 0,
    primaryText = -0xFFFFFF,
    secondaryText = 0xFFfe5e00,
    primaryButton = 0xFFFFFF,
    secondaryButton = 0xFF1c1c1c,
    tertiaryText = 0xFF0591b4,
    historyColor = -0x808080,
    primaryBackground = 0xFFFFFF,
)

val DarkColorTheme = ColorTheme(
    uid = 0,
    primaryText = 0xFFFFFFFFF,
    secondaryText = 0xFFfe5e00,
    primaryButton = -0xFFFFFF,
    secondaryButton = 0xFF1c1c1c,
    tertiaryText = 0xFF0591b4,
    historyColor = -0x808080,
    primaryBackground = -0xFFFFFF,
)

data class DefaultColorHex(
    val black: Long = -0xFFFFFF,
    val white: Long = 0xffffff,
    val lightGray: Long = 0x33333333,
    val gray: Long = -0x808080,
    val darkGray: Long = -0x797979
)

