package com.example.calculator_compose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.calculator_compose.presentation.ui.screen.ApplicationScreen
import com.example.calculator_compose.presentation.ui.theme.AppTheme
import com.example.calculator_compose.presentation.ui.theme.Calculator_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator_composeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.primaryBackground)
                ) {
                    ApplicationScreen()
                }
            }
        }
    }
}