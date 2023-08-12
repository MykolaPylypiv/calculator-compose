package com.example.calculator_compose.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun Calculator_composeTheme(
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalColorProvider provides darkPalette, content = content
    )
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors provided")
}