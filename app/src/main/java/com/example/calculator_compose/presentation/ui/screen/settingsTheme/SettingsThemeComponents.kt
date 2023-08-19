package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.calculator_compose.app.colors
import com.example.calculator_compose.app.language
import com.example.calculator_compose.domain.model.DefaultColorHex

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
    val colors = DefaultColorHex()

    val color1 =
        remember { mutableStateOf(com.example.calculator_compose.app.colors.primaryButton) }
    val color2 =
        remember { mutableStateOf(com.example.calculator_compose.app.colors.primaryButton) }
    val color3 =
        remember { mutableStateOf(com.example.calculator_compose.app.colors.primaryButton) }
    val color4 =
        remember { mutableStateOf(com.example.calculator_compose.app.colors.primaryButton) }
    val color5 =
        remember { mutableStateOf(com.example.calculator_compose.app.colors.primaryButton) }

    Column {
        Row {
            TextButton(
                onClick = {
                    val selectColor = colors.black

                    if (color1.value == com.example.calculator_compose.app.colors.primaryButton) {
                        color1.value = com.example.calculator_compose.app.colors.secondaryButton
                        color2.value = com.example.calculator_compose.app.colors.primaryButton
                        color3.value = com.example.calculator_compose.app.colors.primaryButton
                        color4.value = com.example.calculator_compose.app.colors.primaryButton
                        color5.value = com.example.calculator_compose.app.colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color1.value = com.example.calculator_compose.app.colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
            ) {
                Text(
                    text = language.blackButton,
                    color = com.example.calculator_compose.app.colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = colors.gray

                    if (color3.value == com.example.calculator_compose.app.colors.primaryButton) {
                        color1.value = com.example.calculator_compose.app.colors.primaryButton
                        color2.value = com.example.calculator_compose.app.colors.primaryButton
                        color3.value = com.example.calculator_compose.app.colors.secondaryButton
                        color4.value = com.example.calculator_compose.app.colors.primaryButton
                        color5.value = com.example.calculator_compose.app.colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color3.value = com.example.calculator_compose.app.colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
            ) {
                Text(
                    text = language.grayButton,
                    color = com.example.calculator_compose.app.colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = colors.white

                    if (color5.value == com.example.calculator_compose.app.colors.primaryButton) {
                        color1.value = com.example.calculator_compose.app.colors.primaryButton
                        color2.value = com.example.calculator_compose.app.colors.primaryButton
                        color3.value = com.example.calculator_compose.app.colors.primaryButton
                        color4.value = com.example.calculator_compose.app.colors.primaryButton
                        color5.value = com.example.calculator_compose.app.colors.secondaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color5.value = com.example.calculator_compose.app.colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
            ) {
                Text(
                    text = language.whiteButton,
                    color = com.example.calculator_compose.app.colors.primaryText
                )
            }
        }

        Row {
            TextButton(
                onClick = {
                    val selectColor = colors.darkGray

                    if (color2.value == com.example.calculator_compose.app.colors.primaryButton) {
                        color1.value = com.example.calculator_compose.app.colors.primaryButton
                        color2.value = com.example.calculator_compose.app.colors.secondaryButton
                        color3.value = com.example.calculator_compose.app.colors.primaryButton
                        color4.value = com.example.calculator_compose.app.colors.primaryButton
                        color5.value = com.example.calculator_compose.app.colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color2.value = com.example.calculator_compose.app.colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
            ) {
                Text(
                    text = language.darkButton,
                    color = com.example.calculator_compose.app.colors.primaryText
                )
            }

            TextButton(
                onClick = {
                    val selectColor = colors.lightGray

                    if (color4.value == com.example.calculator_compose.app.colors.primaryButton) {
                        color1.value = com.example.calculator_compose.app.colors.primaryButton
                        color2.value = com.example.calculator_compose.app.colors.primaryButton
                        color3.value = com.example.calculator_compose.app.colors.primaryButton
                        color4.value = com.example.calculator_compose.app.colors.secondaryButton
                        color5.value = com.example.calculator_compose.app.colors.primaryButton

                        section(section = section, color = selectColor, viewModel = viewModel)

                    } else color4.value = com.example.calculator_compose.app.colors.primaryButton
                },
                modifier = Modifier.weight(1F),
                colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
            ) {
                Text(
                    text = language.lightButton,
                    color = com.example.calculator_compose.app.colors.primaryText
                )
            }
        }
    }
}

fun section(color: Long, section: String, viewModel: SettingsThemeViewModel) {
    when (section) {
        "TextColor" -> viewModel.textColor = color
        "SecondaryTextColor" -> viewModel.secondaryText = color
        "TertiaryTextColor" -> viewModel.tertiaryText = color
        "AdditionalTextColor" -> viewModel.additionalText = color
        "ButtonColor" -> viewModel.primaryButton = color
        "SecondaryButtonColor" -> viewModel.secondaryButton = color
        "BackgroundColor" -> viewModel.backgroundColor = color
    }
}