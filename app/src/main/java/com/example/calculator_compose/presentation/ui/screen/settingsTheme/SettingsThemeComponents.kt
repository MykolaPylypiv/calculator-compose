package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.main.language
import com.example.calculator_compose.app.main.variableTheme
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.DefaultColorHex
import com.example.calculator_compose.presentation.ui.theme.AppTheme.colors
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun SettingsThemeTopBar(viewModel: SettingsThemeViewModel, navController: NavController) {
    TopAppBar(backgroundColor = colors.primaryBackground, elevation = 1.dp) {
        IconButton(onClick = { viewModel.navigationToMain(navController = navController) }) {
            val description = "Change theme"
            val iconBack = Icons.Filled.ArrowBackIosNew

            Icon(
                iconBack, contentDescription = description, tint = colors.secondaryText
            )
        }

        Text(
            text = language.value.topBarSettingsTheme,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = colors.primaryText
        )
    }
}

@Composable
fun DarkLightThemeButton(
    contentDescription: String, modifier: Modifier, imageVector: ImageVector, onClick: () -> Unit
) {
    IconButton(
        onClick = onClick, modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp),
            tint = colors.secondaryText
        )
    }
}

@Composable
fun SectionSelectColor(section: String, viewModel: SettingsThemeViewModel) {
    val color = DefaultColorHex()
    val colors = colors

    val color1 = remember { mutableStateOf(colors.primaryButton) }
    val color2 = remember { mutableStateOf(colors.primaryButton) }
    val color3 = remember { mutableStateOf(colors.primaryButton) }
    val color4 = remember { mutableStateOf(colors.primaryButton) }
    val color5 = remember { mutableStateOf(colors.primaryButton) }

    Column {
        val modifier = Modifier.weight(1F)

        Row {
            val onClickBlack = {
                val selectColor = color.black

                if (color1.value == colors.primaryButton) {
                    color1.value = colors.additionalText
                    color2.value = colors.primaryButton
                    color3.value = colors.primaryButton
                    color4.value = colors.primaryButton
                    color5.value = colors.primaryButton

                    section(section = section, color = selectColor, viewModel = viewModel)

                } else color1.value = colors.primaryButton
            }

            TextButton(
                onClick = onClickBlack,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(
                    text = language.value.blackButton, color = colors.primaryText
                )
            }

            val onClickGray = {
                val selectColor = color.gray

                if (color3.value == colors.primaryButton) {
                    color1.value = colors.primaryButton
                    color2.value = colors.primaryButton
                    color3.value = colors.additionalText
                    color4.value = colors.primaryButton
                    color5.value = colors.primaryButton

                    section(section = section, color = selectColor, viewModel = viewModel)

                } else color3.value = colors.primaryButton
            }

            TextButton(
                onClick = onClickGray,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
            ) {
                Text(
                    text = language.value.grayButton, color = colors.primaryText
                )
            }

            val onClickWhite = {
                val selectColor = color.white

                if (color5.value == colors.primaryButton) {
                    color1.value = colors.primaryButton
                    color2.value = colors.primaryButton
                    color3.value = colors.primaryButton
                    color4.value = colors.primaryButton
                    color5.value = colors.additionalText

                    section(section = section, color = selectColor, viewModel = viewModel)

                } else color5.value = colors.primaryButton
            }

            TextButton(
                onClick = onClickWhite,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
            ) {
                Text(
                    text = language.value.whiteButton, color = colors.primaryText
                )
            }
        }

        Row {
            val onClickDarkGray = {
                val selectColor = color.darkGray

                if (color2.value == colors.primaryButton) {
                    color1.value = colors.primaryButton
                    color2.value = colors.additionalText
                    color3.value = colors.primaryButton
                    color4.value = colors.primaryButton
                    color5.value = colors.primaryButton

                    section(section = section, color = selectColor, viewModel = viewModel)

                } else color2.value = colors.primaryButton
            }

            TextButton(
                onClick = onClickDarkGray,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(
                    text = language.value.darkButton, color = colors.primaryText
                )
            }

            val onClickLightGray = {
                val selectColor = color.lightGray

                if (color4.value == colors.primaryButton) {
                    color1.value = colors.primaryButton
                    color2.value = colors.primaryButton
                    color3.value = colors.primaryButton
                    color4.value = colors.additionalText
                    color5.value = colors.primaryButton

                    section(section = section, color = selectColor, viewModel = viewModel)

                } else color4.value = colors.primaryButton
            }

            TextButton(
                onClick = onClickLightGray,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
            ) {
                Text(
                    text = language.value.lightButton, color = colors.primaryText
                )
            }
        }
    }
}

@Composable
fun DarkLightItem(
    viewModel: SettingsThemeViewModel, navController: NavController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(colors.primaryBackground)
    ) {
        val modifier = Modifier.weight(1F)
        val darkIcon = Icons.Filled.DarkMode
        val darkDescription = "Dark model"

        DarkLightThemeButton(
            contentDescription = darkDescription, modifier = modifier, imageVector = darkIcon
        ) {
            viewModel.navigationToMain(navController)
            viewModel.deleteColor()
            variableTheme.value = Strings.DARK
            viewModel.updateVariableTheme(variableTheme.value)
        }

        val lightIcon = Icons.Filled.LightMode
        val lightDescription = "Light mode"

        DarkLightThemeButton(
            contentDescription = lightDescription, modifier = modifier, imageVector = lightIcon
        ) {
            viewModel.navigationToMain(navController)
            viewModel.deleteColor()
            variableTheme.value = Strings.LIGHT
            viewModel.updateVariableTheme(Strings.LIGHT)
        }
    }
}

@Composable
fun TextColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.textColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .height(50.dp)
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        SectionSelectColor(section = "TextColor", viewModel = viewModel)
    }
}

@Composable
fun SecondaryTextColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.secondaryTextColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        SectionSelectColor(section = "SecondaryTextColor", viewModel = viewModel)
    }
}

@Composable
fun ButtonColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.buttonColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        SectionSelectColor(section = "ButtonColor", viewModel = viewModel)
    }
}

@Composable
fun TertiaryTextColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.tertiaryTextColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        SectionSelectColor(section = "TertiaryTextColor", viewModel = viewModel)
    }
}

@Composable
fun AdditionalTextColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    val colors = colors

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.historyColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        val color1 = remember { mutableStateOf(colors.primaryButton) }
        val color2 = remember { mutableStateOf(colors.primaryButton) }

        Row {
            TextButton(
                onClick = {
                    if (color1.value == colors.primaryButton) {
                        color1.value = colors.additionalText
                        color2.value = colors.primaryButton
                        viewModel.historyText = -0x797979
                    } else color1.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(text = language.value.darkButton, color = colors.primaryText)
            }

            TextButton(
                onClick = {
                    if (color2.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.additionalText
                        viewModel.historyText = 0x33333333
                    } else color2.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(text = language.value.lightButton, color = colors.primaryText)
            }
        }
    }
}

@Composable
fun BackgroundColorItem(
    viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color
) {
    val colors = colors

    val controller = rememberColorPickerController()
    val colorCustomTheme = remember { mutableStateOf(colors.primaryBackground) }
    val changeThemeDialog = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.value.backgroundColor}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 15.dp)
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, 0f),
                        strokeWidth = borderSize.toPx()
                    )
                },
            fontSize = 18.sp,
            color = colors.primaryText
        )

        val color1 = remember { mutableStateOf(colors.primaryButton) }
        val color2 = remember { mutableStateOf(colors.primaryButton) }
        val color3 = remember { mutableStateOf(colors.primaryButton) }
        val color4 = remember { mutableStateOf(colors.primaryButton) }
        val color5 = remember { mutableStateOf(colors.primaryButton) }

        Column {
            Row {
                TextButton(
                    onClick = {
                        if (color1.value == colors.primaryButton) {
                            color1.value = colors.additionalText
                            color2.value = colors.primaryButton
                            color3.value = colors.primaryButton
                            color4.value = colors.primaryButton
                            color5.value = colors.primaryButton
                            colorCustomTheme.value = colors.primaryBackground
                            viewModel.backgroundColor = -0xFFFFFF
                        } else color1.value = colors.primaryButton
                    },
                    modifier = Modifier.weight(1F),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
                ) {
                    Text(text = language.value.blackButton, color = colors.primaryText)
                }

                TextButton(
                    onClick = {
                        if (color3.value == colors.primaryButton) {
                            color1.value = colors.primaryButton
                            color2.value = colors.primaryButton
                            color3.value = colors.additionalText
                            color4.value = colors.primaryButton
                            color5.value = colors.primaryButton
                            colorCustomTheme.value = colors.primaryBackground
                            viewModel.backgroundColor = -0x808080
                        } else color3.value = colors.primaryButton
                    },
                    modifier = Modifier.weight(1F),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
                ) {
                    Text(text = language.value.grayButton, color = colors.primaryText)
                }

                TextButton(
                    onClick = {
                        if (color5.value == colors.primaryButton) {
                            color1.value = colors.primaryButton
                            color2.value = colors.primaryButton
                            color3.value = colors.primaryButton
                            color4.value = colors.primaryButton
                            color5.value = colors.additionalText
                            colorCustomTheme.value = colors.primaryBackground
                            viewModel.backgroundColor = 0xffffff
                        } else color5.value = colors.primaryButton
                    },
                    modifier = Modifier.weight(1F),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
                ) {
                    Text(text = language.value.whiteButton, color = colors.primaryText)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = {
                        if (color4.value == colors.primaryButton) {
                            color1.value = colors.primaryButton
                            color2.value = colors.primaryButton
                            color3.value = colors.primaryButton
                            color4.value = colors.additionalText
                            color5.value = colors.primaryButton
                            colorCustomTheme.value = colors.primaryBackground
                            viewModel.backgroundColor = 0x33333333
                        } else color4.value = colors.primaryButton
                    },
                    modifier = Modifier.weight(1F),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
                ) {
                    Text(text = language.value.lightButton, color = colors.primaryText)
                }

                TextButton(
                    onClick = {
                        if (color2.value == colors.primaryButton) {
                            color1.value = colors.primaryButton
                            color2.value = colors.additionalText
                            color3.value = colors.primaryButton
                            color4.value = colors.primaryButton
                            color5.value = colors.primaryButton
                            colorCustomTheme.value = colors.primaryBackground
                            viewModel.backgroundColor = -0x797979
                        } else color2.value = colors.primaryButton
                    },
                    modifier = Modifier.weight(1F),
                    colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
                ) {
                    Text(text = language.value.darkButton, color = colors.primaryText)
                }

                TextButton(
                    onClick = {
                        color1.value = colors.primaryButton
                        color2.value = colors.primaryButton
                        color3.value = colors.primaryButton
                        color4.value = colors.primaryButton
                        color5.value = colors.primaryButton
                        changeThemeDialog.value = true
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.primaryButton),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = language.value.moreButton,
                        fontSize = 14.sp,
                        color = colors.primaryText
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = Strings.EMPTY,
                    Modifier
                        .background(color = colorCustomTheme.value)
                        .width(30.dp)
                        .height(40.dp)
                )
            }
        }

        if (changeThemeDialog.value) {
            AlertDialog(onDismissRequest = {
                changeThemeDialog.value = false
            }, title = {
                Text(fontSize = 22.sp, text = language.value.changeThemeDialog)
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
                        modifier = Modifier.fillMaxWidth(), onClick = {
                            changeThemeDialog.value = false
                            colorCustomTheme.value = controller.selectedColor.value
                            viewModel.backgroundColor =
                                (controller.selectedColor.value.toArgb()).toLong()
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        Text(
                            text = language.value.acceptButton,
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
fun AcceptItem(
    viewModel: SettingsThemeViewModel, navController: NavController, context: Context
) {
    Button(
        onClick = {
            viewModel.navigationToMain(navController)
            viewModel.updateColorTheme(
                ColorTheme(
                    uid = 0,
                    primaryText = viewModel.textColor,
                    secondaryText = viewModel.secondaryText,
                    primaryButton = viewModel.primaryButton,
                    tertiaryText = viewModel.tertiaryText,
                    additionalTextColor = viewModel.historyText,
                    primaryBackground = viewModel.backgroundColor
                )
            )
            variableTheme.value = Strings.CUSTOM
            viewModel.updateVariableTheme(variableTheme.value)
            Toast.makeText(context, language.value.toastChangeTheme, Toast.LENGTH_LONG).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.primaryButton, contentColor = colors.primaryText
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 1.dp,
            pressedElevation = 3.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 4.dp,
            focusedElevation = 4.dp
        )
    ) { Text(text = language.value.acceptButton, fontSize = 18.sp) }
}

fun section(color: Long, section: String, viewModel: SettingsThemeViewModel) {
    when (section) {
        "TextColor" -> viewModel.textColor = color
        "SecondaryTextColor" -> viewModel.secondaryText = color
        "TertiaryTextColor" -> viewModel.tertiaryText = color
        "AdditionalTextColor" -> viewModel.historyText = color
        "ButtonColor" -> viewModel.primaryButton = color
        "BackgroundColor" -> viewModel.backgroundColor = color
    }
}