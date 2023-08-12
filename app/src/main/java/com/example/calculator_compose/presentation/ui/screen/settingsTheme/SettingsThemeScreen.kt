package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.ThemeColors
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.LightColorTheme
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun SettingsThemeScreen(
    navController: NavController, viewModel: SettingsThemeViewModel
) {
    val controller = rememberColorPickerController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ThemeColors.primaryBackground)
    ) {
        TopAppBar(backgroundColor = ThemeColors.primaryBackground) {
            SettingsThemeTopBar(viewModel = viewModel, navController = navController)
        }

        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            item { DarkLightItem(viewModel = viewModel, navController = navController) }

            item { TextColor() }

            item { SecondaryTextColor() }

            item { TertiaryTextColor() }

            item { AdditionalTextColor() }

            item { ButtonColor() }

            item { SecondaryButtonColor() }

            item { BackgroundColor(controller) }

            item { AcceptItem(viewModel = viewModel, navController = navController) }
        })
    }
}

@Composable
fun SettingsThemeTopBar(viewModel: SettingsThemeViewModel, navController: NavController) {
    IconButton(onClick = {
        viewModel.navigationToMain(navController = navController)
    }) {
        Icon(
            Icons.Filled.ArrowBackIosNew,
            contentDescription = "Change theme",
            tint = ThemeColors.secondaryText
        )
    }

    Text(
        text = "Settings theme",
        fontSize = 18.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = ThemeColors.primaryText
    )
}

@Composable
fun DarkLightItem(viewModel: SettingsThemeViewModel, navController: NavController) {
    val colors = ThemeColors

    Row(
        Modifier
            .fillMaxWidth()
            .background(colors.primaryBackground)
    ) {
        DarkLightThemeButton(
            contentDescription = "Dark mode",
            modifier = Modifier.weight(1F),
            imageVector = Icons.Filled.DarkMode
        ) {
            viewModel.navigationToMain(navController)
            viewModel.deleteAll()
        }

        DarkLightThemeButton(
            contentDescription = "Light mode",
            modifier = Modifier.weight(1F),
            imageVector = Icons.Filled.LightMode
        ) {
            viewModel.navigationToMain(navController)
            viewModel.updateTheme(LightColorTheme)
        }
    }
}

@Composable
fun TextColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select text color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        SectionSelectColor(section = "TextColor")
    }
}

@Composable
fun SecondaryTextColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select secondary text color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        SectionSelectColor(section = "SecondaryTextColor")
    }
}

@Composable
fun ButtonColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select button color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        SectionSelectColor(section = "ButtonColor")
    }
}

@Composable
fun SecondaryButtonColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select secondary button color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        SectionSelectColor(section = "SecondaryButtonColor")
    }
}

@Composable
fun TertiaryTextColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select tertiary text color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        SectionSelectColor(section = "TertiaryTextColor")
    }
}

var textColor = LightColorTheme.primaryText
var secondaryText = LightColorTheme.secondaryText
var tertiaryText = LightColorTheme.tertiaryText
var additionalText = LightColorTheme.historyColor
var primaryButton = LightColorTheme.primaryButton
var secondaryButton = LightColorTheme.secondaryButton
var backgroundColor = LightColorTheme.primaryBackground

@Composable
fun AdditionalTextColor() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select history color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        val color1 = remember { mutableStateOf(ThemeColors.primaryButton) }
        val color2 = remember { mutableStateOf(ThemeColors.primaryButton) }

        Row {
            Button(
                onClick = {
                    if (color1.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.secondaryButton
                        color2.value = ThemeColors.primaryButton
                        additionalText = -0x797979
                    } else color1.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(text = "Dark", color = ThemeColors.primaryText)
            }

            Button(
                onClick = {
                    if (color2.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.primaryButton
                        color2.value = ThemeColors.secondaryButton
                        additionalText = 0x33333333
                    } else color2.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(text = "Light", color = ThemeColors.primaryText)
            }
        }
    }
}

@Composable
fun BackgroundColor(controller: ColorPickerController) {
    val colorCustomTheme = remember { mutableStateOf(ThemeColors.primaryBackground) }
    val changeThemeDialog = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select background color",
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontSize = 18.sp,
            color = ThemeColors.primaryText
        )

        val color1 = remember { mutableStateOf(ThemeColors.primaryButton) }
        val color2 = remember { mutableStateOf(ThemeColors.primaryButton) }
        val color3 = remember { mutableStateOf(ThemeColors.primaryButton) }
        val color4 = remember { mutableStateOf(ThemeColors.primaryButton) }
        val color5 = remember { mutableStateOf(ThemeColors.primaryButton) }

        Row {
            Button(
                onClick = {
                    if (color1.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.secondaryButton
                        color2.value = ThemeColors.primaryButton
                        color3.value = ThemeColors.primaryButton
                        color4.value = ThemeColors.primaryButton
                        color5.value = ThemeColors.primaryButton
                        colorCustomTheme.value = ThemeColors.primaryBackground
                        backgroundColor = -0xFFFFFF
                    } else color1.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(text = "Black", color = ThemeColors.primaryText)
            }

            Button(
                onClick = {
                    if (color2.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.primaryButton
                        color2.value = ThemeColors.secondaryButton
                        color3.value = ThemeColors.primaryButton
                        color4.value = ThemeColors.primaryButton
                        color5.value = ThemeColors.primaryButton
                        colorCustomTheme.value = ThemeColors.primaryBackground
                        backgroundColor = -0x797979
                    } else color2.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(text = "Dark", color = ThemeColors.primaryText)
            }

            Button(
                onClick = {
                    if (color3.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.primaryButton
                        color2.value = ThemeColors.primaryButton
                        color3.value = ThemeColors.secondaryButton
                        color4.value = ThemeColors.primaryButton
                        color5.value = ThemeColors.primaryButton
                        colorCustomTheme.value = ThemeColors.primaryBackground
                        backgroundColor = -0x808080
                    } else color3.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
            ) {
                Text(text = "Gray", color = ThemeColors.primaryText)
            }

            Button(
                onClick = {
                    if (color4.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.primaryButton
                        color2.value = ThemeColors.primaryButton
                        color3.value = ThemeColors.primaryButton
                        color4.value = ThemeColors.secondaryButton
                        color5.value = ThemeColors.primaryButton
                        colorCustomTheme.value = ThemeColors.primaryBackground
                        backgroundColor = 0x33333333
                    } else color4.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
            ) {
                Text(text = "Light", color = ThemeColors.primaryText)
            }

            Button(
                onClick = {
                    if (color5.value == ThemeColors.primaryButton) {
                        color1.value = ThemeColors.primaryButton
                        color2.value = ThemeColors.primaryButton
                        color3.value = ThemeColors.primaryButton
                        color4.value = ThemeColors.primaryButton
                        color5.value = ThemeColors.secondaryButton
                        colorCustomTheme.value = ThemeColors.primaryBackground
                        backgroundColor = 0xffffff
                    } else color5.value = ThemeColors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
            ) {
                Text(text = "White", color = ThemeColors.primaryText)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    color1.value = ThemeColors.primaryButton
                    color2.value = ThemeColors.primaryButton
                    color3.value = ThemeColors.primaryButton
                    color4.value = ThemeColors.primaryButton
                    color5.value = ThemeColors.primaryButton
                    changeThemeDialog.value = true
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ThemeColors.primaryButton),
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = "More...", fontSize = 14.sp, color = ThemeColors.primaryText
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "",
                Modifier
                    .background(color = colorCustomTheme.value)
                    .width(75.dp)
                    .height(40.dp)
            )
        }

        if (changeThemeDialog.value) {
            AlertDialog(onDismissRequest = {
                changeThemeDialog.value = false
            }, title = {
                Text(fontSize = 22.sp, text = "Select a theme")
            }, text = {
                IconButton(onClick = {}) {
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(10.dp),
                        controller = controller
                    )
                }
            }, buttons = {
                Row(
                    modifier = Modifier.background(color = Color.DarkGray)
                ) {
                    Spacer(modifier = Modifier.weight(1F))

                    Button(
                        onClick = {
                            changeThemeDialog.value = false
                            colorCustomTheme.value = controller.selectedColor.value
                            backgroundColor = (controller.selectedColor.value.toArgb()).toLong()
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        Text(
                            text = "Accept",
                            fontSize = 22.sp,
                            color = Color.White,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1F))
                }
            })
        }
    }
}

@Composable
fun AcceptItem(viewModel: SettingsThemeViewModel, navController: NavController) {
    Button(
        onClick = {
            viewModel.navigationToMain(navController)
            viewModel.updateTheme(
                ColorTheme(
                    uid = 0,
                    primaryText = textColor,
                    secondaryText = secondaryText,
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    tertiaryText = tertiaryText,
                    historyColor = additionalText,
                    primaryBackground = backgroundColor
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ThemeColors.primaryButton, contentColor = ThemeColors.primaryText
        )
    ) { Text(text = "Accept", fontSize = 18.sp) }
}