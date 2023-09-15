package com.example.calculator_compose.app.main

import androidx.compose.ui.graphics.Color
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.english
import com.example.calculator_compose.app.ukrainian
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import com.example.calculator_compose.presentation.ui.theme.defaultTheme

class MainViewModel(private val db: AppDatabase) {

    fun giveValues() {
        isEnglish.value = try {
            db.languageDao().get().isEnglish
        } catch (e: NullPointerException) {
            true
        }

        language.value = try {
            if (isEnglish.value) english else ukrainian
        } catch (e: NullPointerException) {
            ukrainian
        }

        variableTheme.value = try {
            db.variableThemeDao().get().theme
        } catch (e: NullPointerException) {
            Strings.LIGHT
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
        }
    }


}