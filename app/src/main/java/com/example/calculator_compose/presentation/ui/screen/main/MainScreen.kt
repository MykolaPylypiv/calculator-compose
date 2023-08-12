package com.example.calculator_compose.presentation.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.ThemeColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel, navController: NavController
) {

    Scaffold {
        MainBody(navController, viewModel)
    }
}

@Composable
fun MainBody(
    navController: NavController, viewModel: MainViewModel
) {
    val history = viewModel.history.collectAsState(initial = "")
    val example = viewModel.example.observeAsState()
    val result = viewModel.result.observeAsState()

    val scroll = ScrollState(Int.MAX_VALUE)

    val colors = ThemeColors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.primaryBackground)
    ) {
        TopAppBar(backgroundColor = colors.primaryBackground) {
            Spacer(modifier = Modifier.weight(1F))

            IconButton(onClick = {
                viewModel.navigationToSettingsTheme(navController = navController)
            }) {
                Icon(
                    Icons.Filled.Brush,
                    contentDescription = "Change theme",
                    tint = colors.secondaryText
                )
            }
        }

        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .verticalScroll(scroll),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = history.value.toString(),
                color = colors.additionalText,
                fontSize = 24.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .padding(bottom = 18.dp, end = 8.dp)
                    .fillMaxSize()
            )
        }

        Row {
            Spacer(modifier = Modifier.weight(1F))

            Text(
                text = example.value.toString(),
                color = colors.primaryText,
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 10.dp, end = 5.dp)
            )
        }

        Row {
            Spacer(modifier = Modifier.weight(1F))

            Text(
                text = result.value.toString(),
                color = colors.additionalText,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .background(color = colors.primaryBackground)
                .weight(1F)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "C"
                ) { viewModel.exampleClear() }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "7"
                ) { viewModel.numberPress("7") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "4"
                ) { viewModel.numberPress("4") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "1"
                ) { viewModel.numberPress("1") }

                Button(
                    onClick = { viewModel.navigationToAdditional(navController = navController) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.primaryButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),

                    ) {
                    Icon(
                        Icons.Filled.CropRotate,
                        contentDescription = "Rotate",
                        tint = colors.secondaryText
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {

                Button(
                    onClick = { viewModel.exampleBack() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.primaryButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),

                    ) {
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = colors.secondaryText
                    )
                }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "8"
                ) { viewModel.numberPress("8") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "5"
                ) { viewModel.numberPress("5") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "2"
                ) { viewModel.numberPress("2") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "0"
                ) { viewModel.zeroPress() }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "%"
                ) { viewModel.actionPress("%") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "9"
                ) { viewModel.numberPress("9") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "6"
                ) { viewModel.numberPress("6") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "3"
                ) { viewModel.numberPress("3") }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "."
                ) { viewModel.comaPress() }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), "/"
                ) { viewModel.actionPress("/") }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), "*"
                ) { viewModel.actionPress("*") }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), "-"
                ) { viewModel.actionPress("-") }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), "+"
                ) { viewModel.actionPress("+") }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), "="
                ) {
                    viewModel.equalPress()
                    CoroutineScope(Dispatchers.Default).launch {
                        scroll.scrollTo(Int.MAX_VALUE)
                    }
                }
            }
        }
    }
}