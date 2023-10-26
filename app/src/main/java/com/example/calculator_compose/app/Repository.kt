package com.example.calculator_compose.app

import androidx.compose.runtime.mutableStateOf
import com.example.calculator_compose.presentation.ui.theme.darkPalette

object Repository {

    val customColors = mutableStateOf(darkPalette)
    val systemBarColors = mutableStateOf(darkPalette.primaryBackground)

    val language = mutableStateOf(english)
    val isEnglish = mutableStateOf(true)

    val variableTheme = mutableStateOf(Strings.DARK)

}