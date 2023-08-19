package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calculator_compose.app.colors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsThemeScreen(
    navController: NavController, viewModel: SettingsThemeViewModel
) {
    val context = LocalContext.current

    val borderSize = 1.dp
    val borderColor = Color.LightGray

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = colors.primaryBackground),
        topBar = { SettingsThemeTopBar(viewModel = viewModel, navController = navController) }) {
        LazyColumn(modifier = Modifier.fillMaxSize().background(colors.primaryBackground), content = {
            item {
                DarkLightItem(
                    viewModel = viewModel,
                    navController = navController,
                    context = context,
                )
            }

            item {
                TextColorItem(
                    viewModel = viewModel, borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                SecondaryTextColorItem(
                    viewModel = viewModel,
                    borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                TertiaryTextColorItem(
                    viewModel = viewModel,
                    borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                AdditionalTextColorItem(
                    viewModel = viewModel,
                    borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                ButtonColorItem(
                    viewModel = viewModel,
                    borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                BackgroundColorItem(
                    viewModel = viewModel,
                    borderSize = borderSize,
                    borderColor = borderColor,
                )
            }

            item {
                AcceptItem(
                    viewModel = viewModel, navController = navController, context = context
                )
            }
        })
    }
}