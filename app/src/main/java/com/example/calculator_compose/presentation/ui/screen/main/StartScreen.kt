package com.example.calculator_compose.presentation.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import com.example.calculator_compose.presentation.ui.theme.AppTheme.colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(
    viewModel: StartViewModel, navController: NavController
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = colors.primaryBackground),
        topBar = {
            StartAppBar(
                viewModel = viewModel, navController = navController
            )
        },
        content = {
            StartBody(navController, viewModel)
        })
}

@Composable
fun StartBody(
    navController: NavController, viewModel: StartViewModel
) {
    val history = viewModel.history.collectAsState(initial = "")
    val example = viewModel.example.observeAsState()
    val result = viewModel.result.observeAsState()

    val scroll = ScrollState(Int.MAX_VALUE)

    val borderSize = 1.dp
    val borderColor = Color.LightGray

    var moved by remember { mutableStateOf(0.6F) }

    Column(modifier = Modifier.background(color = colors.primaryBackground)) {
        Row(
            modifier = Modifier
                .weight(moved)
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

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = {
                moved += 0.1F
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Down",
                    tint = colors.primaryText
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {
                moved -= 0.1F
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropUp,
                    contentDescription = "Up",
                    tint = colors.primaryText
                )
            }

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
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                }
                .animateContentSize()
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

                TextButton(
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

                TextButton(
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
    }
}