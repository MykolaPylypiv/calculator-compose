package com.example.calculator_compose.presentation.ui.screen.main

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.calculator_compose.presentation.ui.theme.AppTheme.colors

@Composable
fun DefaultCalcButton(
    modifier: Modifier, text: String, onClick: () -> Unit, background: Color, textColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        modifier = modifier,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun PrimaryButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.primaryText
    )
}

@Composable
fun SecondaryButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.secondaryText,
    )
}

@Composable
fun TertiaryButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.tertiaryText,
    )
}

@Composable
fun AdditionalButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.additionalText
    )
}