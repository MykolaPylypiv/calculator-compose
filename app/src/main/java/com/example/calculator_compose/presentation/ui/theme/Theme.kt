package com.example.calculator_compose.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.calculator_compose.app.Exceptions
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.main.customColors
import java.lang.IllegalArgumentException

@Composable
fun Calculator_composeTheme(
    theme: String,
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        when (theme) {
            Strings.DARK -> LocalColorProvider provides darkPalette
            Strings.LIGHT -> LocalColorProvider provides lightPalette
            Strings.CUSTOM -> LocalColorProvider provides customColors.value
            else -> throw IllegalArgumentException(Exceptions.EXCEPTION_NOT_DEFAULT_COLOR_THEME)
        }, content = content
    )
}

object AppTheme {

    val colors: Colors
        @Composable @ReadOnlyComposable get() = LocalColorProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors provided")
}