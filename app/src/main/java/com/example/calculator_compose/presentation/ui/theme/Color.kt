package com.example.calculator_compose.presentation.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackground: Color,
    val primaryButton: Color,
    val secondaryButton: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val additionalText: Color,
)

val darkPalette = Colors(
    primaryBackground = Color.Black,
    primaryButton = Color.Black,
    secondaryButton = Color(0xFF1c1c1c),
    primaryText = Color.White,
    secondaryText = Color(0xFFfe5e00),
    tertiaryText = Color(0xFF0591b4),
    additionalText = Color.Gray
)

fun defaultTheme(
    primaryText: Color,
    secondaryText: Color,
    primaryButton: Color,
    secondaryButton: Color,
    tertiaryText: Color,
    additionalText: Color,
    primaryBackground: Color,
) = Colors(
    primaryText = primaryText,
    secondaryText = secondaryText,
    primaryButton = primaryButton,
    secondaryButton = secondaryButton,
    tertiaryText = tertiaryText,
    additionalText = additionalText,
    primaryBackground = primaryBackground,
)