package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.calculator_compose.app.colors
import com.example.calculator_compose.app.language
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.DarkColorTheme
import com.example.calculator_compose.domain.model.DefaultColorHex
import com.example.calculator_compose.domain.model.LightColorTheme
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun SettingsThemeTopBar(viewModel: SettingsThemeViewModel, navController: NavController) {
    TopAppBar(backgroundColor = colors.primaryBackground, elevation = 1.dp) {
        IconButton(onClick = {
            viewModel.navigationToMain(navController = navController)
        }) {
            Icon(
                Icons.Filled.ArrowBackIosNew,
                contentDescription = "Change theme",
                tint = colors.secondaryText
            )
        }

        Text(
            text = language.topBarSettingsTheme,
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

    val color1 = remember { mutableStateOf(colors.primaryButton) }
    val color2 = remember { mutableStateOf(colors.primaryButton) }
    val color3 = remember { mutableStateOf(colors.primaryButton) }
    val color4 = remember { mutableStateOf(colors.primaryButton) }
    val color5 = remember { mutableStateOf(colors.primaryButton) }

    Column {
        Row {
            TextButton(
                onClick = {
                    val selectColor = color.black

                    if (color1.value == colors.primaryButton) {
                        color1.value = colors.additionalText
                        color2.value = colors.primaryButton
                        color3.value = colors.primaryButton
                        color4.value = colors.primaryButton
                        color5.value = colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color1.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(
                    text = language.blackButton,
                    color = colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = color.gray

                    if (color3.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.primaryButton
                        color3.value = colors.additionalText
                        color4.value = colors.primaryButton
                        color5.value = colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color3.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
            ) {
                Text(
                    text = language.grayButton,
                    color = colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = color.white

                    if (color5.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.primaryButton
                        color3.value = colors.primaryButton
                        color4.value = colors.primaryButton
                        color5.value = colors.additionalText

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color5.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
            ) {
                Text(
                    text = language.whiteButton,
                    color = colors.primaryText
                )
            }
        }

        Row {
            TextButton(
                onClick = {
                    val selectColor = color.darkGray

                    if (color2.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.additionalText
                        color3.value = colors.primaryButton
                        color4.value = colors.primaryButton
                        color5.value = colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color2.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(
                    text = language.darkButton,
                    color = colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = color.lightGray

                    if (color4.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.primaryButton
                        color3.value = colors.primaryButton
                        color4.value = colors.additionalText
                        color5.value = colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color4.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
            ) {
                Text(
                    text = language.lightButton,
                    color = colors.primaryText
                )
            }
        }
    }
}

@Composable
fun DarkLightItem(
    viewModel: SettingsThemeViewModel, navController: NavController, context: Context
) {
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
            viewModel.updateTheme(DarkColorTheme)
            Toast.makeText(context, language.toastChangeTheme, Toast.LENGTH_LONG).show()
        }

        DarkLightThemeButton(
            contentDescription = "Light mode",
            modifier = Modifier.weight(1F),
            imageVector = Icons.Filled.LightMode
        ) {
            viewModel.navigationToMain(navController)
            viewModel.updateTheme(LightColorTheme)
            Toast.makeText(context, language.toastChangeTheme, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun TextColorItem(viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.textColor}",
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
            text = "\n${language.secondaryTextColor}",
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
            text = "\n${language.buttonColor}",
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
            text = "\n${language.tertiaryTextColor}",
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.historyColor}",
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
                        viewModel.additionalText = -0x797979
                    } else color1.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(text = language.darkButton, color = colors.primaryText)
            }

            TextButton(
                onClick = {
                    if (color2.value == colors.primaryButton) {
                        color1.value = colors.primaryButton
                        color2.value = colors.additionalText
                        viewModel.additionalText = 0x33333333
                    } else color2.value = colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(text = language.lightButton, color = colors.primaryText)
            }
        }
    }
}

@Composable
fun BackgroundColorItem(
    viewModel: SettingsThemeViewModel, borderSize: Dp, borderColor: Color
) {
    val controller = rememberColorPickerController()
    val colorCustomTheme = remember { mutableStateOf(colors.primaryBackground) }
    val changeThemeDialog = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "\n${language.backgroundColor}",
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
                    Text(text = language.blackButton, color = colors.primaryText)
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
                    Text(text = language.grayButton, color = colors.primaryText)
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
                    Text(text = language.whiteButton, color = colors.primaryText)
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
                    Text(text = language.lightButton, color = colors.primaryText)
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
                    Text(text = language.darkButton, color = colors.primaryText)
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
                        text = language.moreButton, fontSize = 14.sp, color = colors.primaryText
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
                Text(fontSize = 22.sp, text = language.changeThemeDialog)
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
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            changeThemeDialog.value = false
                            colorCustomTheme.value = controller.selectedColor.value
                            viewModel.backgroundColor =
                                (controller.selectedColor.value.toArgb()).toLong()
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        Text(
                            text = language.acceptButton,
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
            viewModel.updateTheme(
                ColorTheme(
                    uid = 0,
                    primaryText = viewModel.textColor,
                    secondaryText = viewModel.secondaryText,
                    primaryButton = viewModel.primaryButton,
                    tertiaryText = viewModel.tertiaryText,
                    additionalTextColor = viewModel.additionalText,
                    primaryBackground = viewModel.backgroundColor
                )
            )
            Toast.makeText(context, language.toastChangeTheme, Toast.LENGTH_LONG).show()
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
    ) { Text(text = language.acceptButton, fontSize = 18.sp) }
}

fun section(color: Long, section: String, viewModel: SettingsThemeViewModel) {
    when (section) {
        "TextColor" -> viewModel.textColor = color
        "SecondaryTextColor" -> viewModel.secondaryText = color
        "TertiaryTextColor" -> viewModel.tertiaryText = color
        "AdditionalTextColor" -> viewModel.additionalText = color
        "ButtonColor" -> viewModel.primaryButton = color
        "BackgroundColor" -> viewModel.backgroundColor = color
    }
}