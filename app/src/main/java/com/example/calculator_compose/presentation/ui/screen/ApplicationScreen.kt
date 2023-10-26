package com.example.calculator_compose.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculator_compose.navigation.NavigationTree
import com.example.calculator_compose.presentation.ui.screen.additional.AdditionalScreen
import com.example.calculator_compose.presentation.ui.screen.additional.AdditionalViewModel
import com.example.calculator_compose.presentation.ui.screen.main.StartScreen
import com.example.calculator_compose.presentation.ui.screen.main.StartViewModel
import com.example.calculator_compose.presentation.ui.screen.settingsTheme.SettingsThemeScreen
import com.example.calculator_compose.presentation.ui.screen.settingsTheme.SettingsThemeViewModel

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Start.name) {
        composable(NavigationTree.Start.name) {
            val startViewModel = hiltViewModel<StartViewModel>()
            StartScreen(viewModel = startViewModel, navController = navController)
        }
        composable(NavigationTree.Additional.name) {
            val additionalViewModel = hiltViewModel<AdditionalViewModel>()
            EnterAnimation {
                AdditionalScreen(viewModel = additionalViewModel, navController = navController)
            }
        }
        composable(NavigationTree.SettingsTheme.name) {
            val settingsThemeViewModel = hiltViewModel<SettingsThemeViewModel>()
            EnterAnimation {
                SettingsThemeScreen(
                    viewModel = settingsThemeViewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = { -80 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    ) {
        content()
    }
}