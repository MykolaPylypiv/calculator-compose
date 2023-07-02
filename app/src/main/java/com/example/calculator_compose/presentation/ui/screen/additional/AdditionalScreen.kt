package com.example.calculator_compose.presentation.ui.screen.additional

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CropRotate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.presentation.ui.screen.main.AdditionalButton
import com.example.calculator_compose.presentation.ui.screen.main.PrimaryButton
import com.example.calculator_compose.presentation.ui.screen.main.SecondaryButton
import com.example.calculator_compose.presentation.ui.screen.main.TertiaryButton
import com.example.calculator_compose.presentation.ui.theme.AppTheme

@Composable
fun AdditionalScreen(
    navController: NavController, viewModel: AdditionalViewModel
) {
    val example = viewModel.example.observeAsState()
    val result = viewModel.result.observeAsState()
    val degrees = viewModel.degrees.observeAsState()
    val history = viewModel.history.collectAsState(initial = "")
    val scroll = rememberScrollState()

    val degreesEnabled = viewModel.degreesEnabled.observeAsState()

    val sinText = viewModel.sinText.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.primaryBackground)
    ) {
        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .verticalScroll(scroll),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = history.value.toString(),
                color = AppTheme.colors.additionalText,
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
                color = AppTheme.colors.primaryText,
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 10.dp, end = 5.dp)
            )
        }

        Row {
            Spacer(modifier = Modifier.weight(1F))

            Text(
                text = result.value.toString(),
                color = AppTheme.colors.additionalText,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 20.dp, end = 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .background(color = AppTheme.colors.primaryBackground)
                .weight(1.8F)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = "2nd",
                    enabled = viewModel.enabledTwoND()
                ) {
                    viewModel.twoND()
                }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "x^y"
                ) { viewModel.actionPress("^") }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "√"
                ) { viewModel.squareRootPress() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "X!"
                ) { viewModel.factorialPress() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "1/x"
                ) {
                    viewModel.actionPress("^")
                    viewModel.leftBracket()
                    viewModel.numberPress("0")
                    viewModel.actionPress("-")
                    viewModel.numberPress("1")
                    viewModel.rightBracket()
                }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "π"
                ) { viewModel.letterNumPress("π") }

                Button(
                    onClick = { viewModel.navigationToAdditional(navController = navController) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primaryButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),

                    ) {
                    Icon(
                        Icons.Filled.CropRotate,
                        contentDescription = "Rotate",
                        tint = AppTheme.colors.secondaryText
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = degrees.value.toString(),
                    enabled = degreesEnabled.value!!
                ) { viewModel.converting() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.ACTION_LG.dropLast(1)
                ) { viewModel.trigonometricPress(Strings.ACTION_LG) }

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

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "e"
                ) { viewModel.letterNumPress("e") }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = viewModel.sinText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(sinText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.ACTION_LN.dropLast(1)
                ) { viewModel.trigonometricPress(Strings.ACTION_LN) }

                Button(
                    onClick = { viewModel.exampleBack() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primaryButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),

                    ) {
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = AppTheme.colors.secondaryText
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
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = viewModel.cosText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(viewModel.cosText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = "("
                ) { viewModel.leftBracket() }

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
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = viewModel.tanText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(viewModel.tanText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = ")"
                ) { viewModel.rightBracket() }

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
                ) { viewModel.equalPress() }
            }

        }
    }
}