package com.example.calculator_compose.app

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.example.calculator_compose.presentation.ui.theme.darkPalette
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()