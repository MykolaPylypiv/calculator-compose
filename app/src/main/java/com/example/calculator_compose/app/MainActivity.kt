package com.example.calculator_compose.app

import android.annotation.SuppressLint
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import javax.inject.Inject

var ThemeColors: Colors = darkPalette

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: AppDatabase

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            ThemeColors = if (db.colorThemeDao().get().isEmpty()) darkPalette else {
                defaultTheme(
                    primaryText = Color(db.colorThemeDao().get().last().primaryText),
                    secondaryText = Color(db.colorThemeDao().get().last().secondaryText),
                    primaryButton = Color(db.colorThemeDao().get().last().primaryButton),
                    secondaryButton = Color(db.colorThemeDao().get().last().secondaryButton),
                    tertiaryText = Color(db.colorThemeDao().get().last().tertiaryText),
                    additionalText = Color(db.colorThemeDao().get().last().historyColor),
                    primaryBackground = Color(db.colorThemeDao().get().last().primaryBackground),
                )
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

