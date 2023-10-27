package com.example.calculator_compose.app.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.calculator_compose.app.Language
import com.example.calculator_compose.app.Repository.customColors
import com.example.calculator_compose.app.Repository.isEnglish
import com.example.calculator_compose.app.Repository.language
import com.example.calculator_compose.app.Repository.systemBarColors
import com.example.calculator_compose.app.Repository.variableTheme
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import com.example.calculator_compose.presentation.ui.theme.defaultTheme
import com.example.calculator_compose.presentation.ui.theme.lightPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val db: AppDatabase): ViewModel() {

    fun giveValues() {
        isEnglish.value = try {
            db.languageDao().get().isEnglish
        } catch (e: NullPointerException) {
            true
        }

        language.value = try {
            if (isEnglish.value) Language.English else Language.Ukrainian
        } catch (e: NullPointerException) {
            Language.Ukrainian
        }

        variableTheme.value = try {
            db.variableThemeDao().get().theme
        } catch (e: NullPointerException) {
            Strings.LIGHT
        }

        if (variableTheme.value == Strings.DARK) {
            systemBarColors.value = darkPalette.primaryBackground
        }

        if (variableTheme.value == Strings.LIGHT) {
            systemBarColors.value = lightPalette.primaryBackground
        }

        if (variableTheme.value == Strings.CUSTOM) {
            val themeColors = db.colorThemeDao().get()

            customColors.value = try {
                defaultTheme(
                    primaryText = Color(themeColors.primaryText),
                    secondaryText = Color(themeColors.secondaryText),
                    primaryButton = Color(themeColors.primaryButton),
                    tertiaryText = Color(themeColors.tertiaryText),
                    additionalText = Color(themeColors.additionalTextColor),
                    primaryBackground = Color(themeColors.primaryBackground),
                )
            } catch (e: NullPointerException) {
                darkPalette
            }

            systemBarColors.value = customColors.value.primaryBackground
        }
    }
}