package com.example.calculator_compose.presentation.ui.screen.additional

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CropRotate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.Strings.ACTION_DIVISION
import com.example.calculator_compose.app.Strings.ACTION_MINUS
import com.example.calculator_compose.app.Strings.ACTION_MULTIPLY
import com.example.calculator_compose.app.Strings.ACTION_PERCENT
import com.example.calculator_compose.app.Strings.ACTION_PLUS
import com.example.calculator_compose.app.Strings.ACTION_POW
import com.example.calculator_compose.app.Strings.LEFT_BRACKET
import com.example.calculator_compose.app.Strings.NUMBER_E
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
import com.example.calculator_compose.app.Strings.RIGHT_BRACKET
import com.example.calculator_compose.app.Strings.TEXT_CLEAR_CALCULATION
import com.example.calculator_compose.app.Strings.TEXT_EQUAL
import com.example.calculator_compose.presentation.ui.screen.main.AdditionalButton
import com.example.calculator_compose.presentation.ui.screen.main.PrimaryButton
import com.example.calculator_compose.presentation.ui.screen.main.SecondaryButton
import com.example.calculator_compose.presentation.ui.screen.main.TertiaryButton
import com.example.calculator_compose.presentation.ui.theme.AppTheme.colors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdditionalScreen(
    navController: NavController, viewModel: AdditionalViewModel
) {
    Scaffold {
        AdditionalBody(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun AdditionalBody(viewModel: AdditionalViewModel, navController: NavController) {
    val example = viewModel.example.observeAsState()
    val result = viewModel.result.observeAsState()
    val degrees = viewModel.degrees.observeAsState()
    val history = viewModel.history.collectAsState(initial = "")
    val scroll = ScrollState(Int.MAX_VALUE)

    val degreesEnabled = viewModel.degreesEnabled.observeAsState()

    val sinText = viewModel.sinText.observeAsState()

    val borderSize = 1.dp
    val borderColor = Color.LightGray

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.primaryBackground)
    ) {
        Row(
            modifier = Modifier
                .weight(0.9F)
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
                modifier = Modifier.padding(bottom = 20.dp, end = 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .background(color = colors.primaryBackground)
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
                        .weight(1F)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, 0f),
                                strokeWidth = borderSize.toPx()
                            )
                        }, text = Strings.TEXT_TWO_ND, enabled = viewModel.enabledTwoND()
                ) {
                    viewModel.twoND()
                }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.TEXT_ACTION_POW
                ) { viewModel.actionPress(ACTION_POW) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.ACTION_SQUARE_ROOT
                ) { viewModel.squareRootPress() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.TEXT_FACTORIAL
                ) { viewModel.factorialPress() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.TEXT_POW_MINUS_ONE
                ) {
                    viewModel.actionPress(ACTION_POW)
                    viewModel.leftBracket()
                    viewModel.numberPress(NUMBER_ZERO)
                    viewModel.actionPress(ACTION_MINUS)
                    viewModel.numberPress(NUMBER_ONE)
                    viewModel.rightBracket()
                }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = Strings.NUMBER_P
                ) { viewModel.letterNumPress(Strings.NUMBER_P) }

                TextButton(
                    onClick = { viewModel.navigationToMain(navController = navController) },
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
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, 0f),
                                strokeWidth = borderSize.toPx()
                            )
                        }, text = degrees.value.toString(), enabled = degreesEnabled.value!!
                ) { viewModel.converting() }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = Strings.ACTION_LG.dropLast(1)
                ) { viewModel.trigonometricPress(Strings.ACTION_LG) }

                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = TEXT_CLEAR_CALCULATION
                ) { viewModel.exampleClear() }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_SEVEN
                ) {
                    viewModel.numberPress(NUMBER_SEVEN)
                }

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

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = NUMBER_E
                ) { viewModel.letterNumPress(NUMBER_E) }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, 0f),
                                strokeWidth = borderSize.toPx()
                            )
                        }, text = viewModel.sinText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(sinText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    text = Strings.ACTION_LN.dropLast(1)
                ) { viewModel.trigonometricPress(Strings.ACTION_LN) }

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
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, 0f),
                                strokeWidth = borderSize.toPx()
                            )
                        }, text = viewModel.cosText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(viewModel.cosText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = LEFT_BRACKET
                ) { viewModel.leftBracket() }

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
                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .drawBehind {
                            drawLine(
                                color = borderColor,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, 0f),
                                strokeWidth = borderSize.toPx()
                            )
                        }, text = viewModel.tanText.value.toString().dropLast(1)
                ) { viewModel.trigonometricPress(viewModel.tanText.value.toString()) }

                AdditionalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F), text = RIGHT_BRACKET
                ) { viewModel.rightBracket() }

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
                ) { viewModel.equalPress() }
            }

        }
    }
}
