package com.example.calculator_compose.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator_compose.app.Repository.systemBarColors
import com.example.calculator_compose.app.Repository.variableTheme
import com.example.calculator_compose.presentation.ui.screen.ApplicationScreen
import com.example.calculator_compose.presentation.ui.theme.Calculator_composeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val systemUiController = rememberSystemUiController()

            viewModel.giveValues()

            Calculator_composeTheme(theme = variableTheme.value) {
                ApplicationScreen()
            }

            systemUiController.setSystemBarsColor(color = systemBarColors.value)
        }

    }
}

