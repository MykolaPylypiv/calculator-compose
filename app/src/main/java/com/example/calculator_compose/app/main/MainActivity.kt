package com.example.calculator_compose.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.example.calculator_compose.app.Repository.systemBarColors
import com.example.calculator_compose.app.Repository.variableTheme
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.english
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.presentation.ui.screen.ApplicationScreen
import com.example.calculator_compose.presentation.ui.theme.Calculator_composeTheme
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel(db)

        setContent {
            val systemUiController = rememberSystemUiController()

            viewModel.giveValues()

            Calculator_composeTheme(theme = variableTheme.value) {
                ApplicationScreen()
            }

            systemUiController.setSystemBarsColor(color = systemBarColors.value)
        }

    }
}

