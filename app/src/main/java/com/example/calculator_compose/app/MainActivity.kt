package com.example.calculator_compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.presentation.ui.screen.ApplicationScreen
import com.example.calculator_compose.presentation.ui.theme.Colors
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import com.example.calculator_compose.presentation.ui.theme.defaultTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

var colors: Colors = darkPalette
lateinit var language: Language

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val themeColors = db.colorThemeDao().get()
            val systemUiController = rememberSystemUiController()

            language = try {
                if (db.languageDao().get().isEnglish) english else ukrainian
            } catch (e: NullPointerException) {
                ukrainian
            }

            colors = try {
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.primaryBackground)
            ) {
                ApplicationScreen()
            }

            systemUiController.setSystemBarsColor(color = colors.primaryBackground)
        }

    }
}

