package com.example.calculator_compose.presentation.ui.screen.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.History
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.Strings.ACTION_DIVISION
import com.example.calculator_compose.app.Strings.ACTION_MINUS
import com.example.calculator_compose.app.Strings.ACTION_MULTIPLY
import com.example.calculator_compose.app.Strings.ACTION_PERCENT
import com.example.calculator_compose.app.Strings.ACTION_PLUS
import com.example.calculator_compose.app.Strings.NUMBER_EIGHT
import com.example.calculator_compose.app.Strings.NUMBER_FIVE
import com.example.calculator_compose.app.Strings.NUMBER_FOUR
import com.example.calculator_compose.app.Strings.NUMBER_NINE
import com.example.calculator_compose.app.Strings.NUMBER_ONE
import com.example.calculator_compose.app.Strings.NUMBER_SEVEN
import com.example.calculator_compose.app.Strings.NUMBER_SIX
import com.example.calculator_compose.app.Strings.NUMBER_THREE
import com.example.calculator_compose.app.Strings.NUMBER_TWO
import com.example.calculator_compose.app.Strings.NUMBER_ZERO
import com.example.calculator_compose.app.Strings.POINT
import com.example.calculator_compose.app.Strings.TEXT_CLEAR_CALCULATION
import com.example.calculator_compose.app.Strings.TEXT_EQUAL
import com.example.calculator_compose.app.colors
import com.example.calculator_compose.app.language
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

    val changeLanguage = remember { mutableStateOf(viewModel.textChangeLanguage()) }
    val deleteHistoryDialog = remember { mutableStateOf(false) }

    val scroll = ScrollState(Int.MAX_VALUE)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.primaryBackground)
    ) {
        TopAppBar(backgroundColor = colors.primaryBackground) {
            Spacer(modifier = Modifier.weight(1F))

            Button(
                onClick = {
                    changeLanguage.value = viewModel.changeText(changeLanguage.value)
                    viewModel.updateLanguage(changeLanguage.value)
                    Toast.makeText(context, language.toastChangeLanguage, Toast.LENGTH_LONG).show()
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = colors.primaryBackground, contentColor = colors.secondaryText
                )
            ) {
                Text(text = changeLanguage.value)
            }

            IconButton(onClick = {
                deleteHistoryDialog.value = true
            }) {
                Icon(
                    Icons.Rounded.History,
                    contentDescription = "Delete history",
                    tint = colors.secondaryText
                )
            }

            IconButton(onClick = {
                viewModel.navigationToSettingsTheme(navController = navController)
            }) {
                Icon(
                    Icons.Rounded.Brush,
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
                        .weight(1F), text = TEXT_CLEAR_CALCULATION
                ) { viewModel.exampleClear() }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_SEVEN
                ) { viewModel.numberPress(NUMBER_SEVEN) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_FOUR
                ) { viewModel.numberPress(NUMBER_FOUR) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_ONE
                ) { viewModel.numberPress(NUMBER_ONE) }

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
                        .weight(1F), text = NUMBER_EIGHT
                ) { viewModel.numberPress(NUMBER_EIGHT) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_FIVE
                ) { viewModel.numberPress(NUMBER_FIVE) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_TWO
                ) { viewModel.numberPress(NUMBER_TWO) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_ZERO
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
                        .weight(1F), text = ACTION_PERCENT
                ) { viewModel.actionPress(ACTION_PERCENT) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_NINE
                ) { viewModel.numberPress(NUMBER_NINE) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_SIX
                ) { viewModel.numberPress(NUMBER_SIX) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_THREE
                ) { viewModel.numberPress(NUMBER_THREE) }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = POINT
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
                        .weight(1F), ACTION_DIVISION
                ) { viewModel.actionPress(ACTION_DIVISION) }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), ACTION_MULTIPLY
                ) { viewModel.actionPress(ACTION_MULTIPLY) }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), ACTION_MINUS
                ) { viewModel.actionPress(ACTION_MINUS) }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), ACTION_PLUS
                ) { viewModel.actionPress(ACTION_PLUS) }

                TertiaryButton(
                    Modifier
                        .fillMaxWidth()
                        .weight(1F), TEXT_EQUAL
                ) {
                    viewModel.equalPress()
                    CoroutineScope(Dispatchers.Default).launch {
                        scroll.scrollTo(Int.MAX_VALUE)
                    }
                }
            }
        }

        if (deleteHistoryDialog.value) {
            AlertDialog(onDismissRequest = {
                deleteHistoryDialog.value = false
            }, title = {
                Text(fontSize = 22.sp, text = language.deleteHistoryDialog)
            }, text = {}, buttons = {
                Row(
                    modifier = Modifier.background(color = Color.DarkGray)
                ) {
                    Button(
                        onClick = {
                            deleteHistoryDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(
                            text = language.dismissButton,
                            fontSize = 22.sp,
                            color = colors.primaryText,
                        )
                    }

                    Button(
                        onClick = {
                            deleteHistoryDialog.value = false
                            viewModel.resetHistory()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(
                            text = language.acceptButton,
                            fontSize = 22.sp,
                            color = colors.primaryText,
                        )
                    }
                }
            })
        }
    }
}