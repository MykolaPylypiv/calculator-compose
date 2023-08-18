package com.example.calculator_compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorLong
import androidx.lifecycle.LiveData
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.presentation.ui.screen.ApplicationScreen
import com.example.calculator_compose.presentation.ui.theme.Colors
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import com.example.calculator_compose.presentation.ui.theme.defaultTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

var ThemeColors: Colors = darkPalette

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val colors = db.colorThemeDao().get()

            ThemeColors =
                try {
                    defaultTheme(
                        primaryText = Color(colors.primaryText),
                        secondaryText = Color(colors.secondaryText),
                        primaryButton = Color(colors.primaryButton),
                        secondaryButton = Color(colors.secondaryButton),
                        tertiaryText = Color(colors.tertiaryText),
                        additionalText = Color(colors.historyColor),
                        primaryBackground = Color(colors.primaryBackground),
                    )
                } catch (e: NullPointerException) {
                    darkPalette
                }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ThemeColors.primaryBackground)
            ) {
                ApplicationScreen()
            }
        }
    }
}

