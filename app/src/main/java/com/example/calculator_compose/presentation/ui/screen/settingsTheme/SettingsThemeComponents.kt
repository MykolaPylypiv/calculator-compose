package com.example.calculator_compose.presentation.ui.screen.settingsTheme

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
import com.example.calculator_compose.app.ThemeColors
import com.example.calculator_compose.domain.model.DefaultColorHex

@Composable
fun DarkLightThemeButton(
    contentDescription: String, modifier: Modifier, imageVector: ImageVector, onClick: () -> Unit
) {
    val colors = ThemeColors

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
fun SectionSelectColor(section: String) {
    val colors = DefaultColorHex()

    val color1 = remember { mutableStateOf(ThemeColors.primaryButton) }
    val color2 = remember { mutableStateOf(ThemeColors.primaryButton) }
    val color3 = remember { mutableStateOf(ThemeColors.primaryButton) }
    val color4 = remember { mutableStateOf(ThemeColors.primaryButton) }
    val color5 = remember { mutableStateOf(ThemeColors.primaryButton) }

    Row {
        Button(
            onClick = {
                val selectColor = colors.black

                if (color1.value == ThemeColors.primaryButton) {
                    color1.value = ThemeColors.secondaryButton
                    color2.value = ThemeColors.primaryButton
                    color3.value = ThemeColors.primaryButton
                    color4.value = ThemeColors.primaryButton
                    color5.value = ThemeColors.primaryButton

                    section(section = section, color = selectColor)

                } else color1.value = ThemeColors.primaryButton
            },
            modifier = Modifier.weight(1F),
            colors = ButtonDefaults.buttonColors(backgroundColor = color1.value)
        ) {
            Text(text = "Black", color = ThemeColors.primaryText)
        }

        Button(
            onClick = {
                val selectColor = colors.darkGray

                if (color2.value == ThemeColors.primaryButton) {
                    color1.value = ThemeColors.primaryButton
                    color2.value = ThemeColors.secondaryButton
                    color3.value = ThemeColors.primaryButton
                    color4.value = ThemeColors.primaryButton
                    color5.value = ThemeColors.primaryButton

                    section(section = section, color = selectColor)

                } else color2.value = ThemeColors.primaryButton
            },
            modifier = Modifier.weight(1F),
            colors = ButtonDefaults.buttonColors(backgroundColor = color2.value)
        ) {
            Text(text = "Dark", color = ThemeColors.primaryText)
        }

        Button(
            onClick = {
                val selectColor = colors.gray

                if (color3.value == ThemeColors.primaryButton) {
                    color1.value = ThemeColors.primaryButton
                    color2.value = ThemeColors.primaryButton
                    color3.value = ThemeColors.secondaryButton
                    color4.value = ThemeColors.primaryButton
                    color5.value = ThemeColors.primaryButton

                    section(section = section, color = selectColor)

                } else color3.value = ThemeColors.primaryButton
            },
            modifier = Modifier.weight(1F),
            colors = ButtonDefaults.buttonColors(backgroundColor = color3.value)
        ) {
            Text(text = "Gray", color = ThemeColors.primaryText)
        }

        Button(
            onClick = {
                val selectColor = colors.lightGray

                if (color4.value == ThemeColors.primaryButton) {
                    color1.value = ThemeColors.primaryButton
                    color2.value = ThemeColors.primaryButton
                    color3.value = ThemeColors.primaryButton
                    color4.value = ThemeColors.secondaryButton
                    color5.value = ThemeColors.primaryButton

                    section(section = section, color = selectColor)

                } else color4.value = ThemeColors.primaryButton
            },
            modifier = Modifier.weight(1F),
            colors = ButtonDefaults.buttonColors(backgroundColor = color4.value)
        ) {
            Text(text = "Light", color = ThemeColors.primaryText)
        }

        Button(
            onClick = {
                val selectColor = colors.white

                if (color5.value == ThemeColors.primaryButton) {
                    color1.value = ThemeColors.primaryButton
                    color2.value = ThemeColors.primaryButton
                    color3.value = ThemeColors.primaryButton
                    color4.value = ThemeColors.primaryButton
                    color5.value = ThemeColors.secondaryButton

                    section(section = section, color = selectColor)

                } else color5.value = ThemeColors.primaryButton
            },
            modifier = Modifier.weight(1F),
            colors = ButtonDefaults.buttonColors(backgroundColor = color5.value)
        ) {
            Text(text = "White", color = ThemeColors.primaryText)
        }
    }
}

fun section(color: Long, section: String) {
    when (section) {
        "TextColor" -> textColor = color
        "SecondaryTextColor" -> secondaryText = color
        "TertiaryTextColor" -> tertiaryText = color
        "AdditionalTextColor" -> additionalText = color
        "ButtonColor" -> primaryButton = color
        "SecondaryButtonColor" -> secondaryButton = color
        "BackgroundColor" -> backgroundColor = color
    }
}