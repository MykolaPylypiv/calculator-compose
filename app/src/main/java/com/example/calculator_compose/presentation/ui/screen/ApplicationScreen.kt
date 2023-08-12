package com.example.calculator_compose.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculator_compose.navigation.NavigationTree
import com.example.calculator_compose.presentation.ui.screen.additional.AdditionalScreen
import com.example.calculator_compose.presentation.ui.screen.additional.AdditionalViewModel
import com.example.calculator_compose.presentation.ui.screen.main.MainScreen
import com.example.calculator_compose.presentation.ui.screen.main.MainViewModel
import com.example.calculator_compose.presentation.ui.screen.settingsTheme.SettingsThemeScreen
import com.example.calculator_compose.presentation.ui.screen.settingsTheme.SettingsThemeViewModel

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Main.name) {
        composable(NavigationTree.Main.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(viewModel = mainViewModel, navController = navController)
        }
        composable(NavigationTree.Additional.name) {
            val additionalViewModel = hiltViewModel<AdditionalViewModel>()
            AdditionalScreen(viewModel = additionalViewModel, navController = navController)
        }
        composable(NavigationTree.SettingsTheme.name) {
            val settingsThemeViewModel = hiltViewModel<SettingsThemeViewModel>()
            SettingsThemeScreen(viewModel = settingsThemeViewModel, navController = navController)
        }
    }
}