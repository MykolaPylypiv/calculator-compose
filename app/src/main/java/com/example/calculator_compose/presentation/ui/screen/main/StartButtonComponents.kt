package com.example.calculator_compose.presentation.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator_compose.app.Repository.isEnglish
import com.example.calculator_compose.app.Repository.language
import com.example.calculator_compose.presentation.ui.theme.AppTheme.colors

@Composable
fun StartAppBar(viewModel: StartViewModel, navController: NavController) {
    val deleteHistoryDialog = remember { mutableStateOf(false) }
    val textChangeLanguage =
        remember { mutableStateOf(viewModel.textButtonChangeLanguage(isEnglish.value)) }

    TopAppBar(backgroundColor = colors.primaryBackground, elevation = 1.dp) {
        Spacer(modifier = Modifier.weight(1F))

        TextButton(
            onClick = {
                isEnglish.value = !isEnglish.value
                viewModel.updateLanguage(isEnglish.value)
                textChangeLanguage.value = viewModel.textButtonChangeLanguage(isEnglish.value)
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = colors.primaryBackground, contentColor = colors.secondaryText
            )
        ) {
            Text(text = textChangeLanguage.value)
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

    if (deleteHistoryDialog.value) {
        AlertDialog(shape = RoundedCornerShape(20), onDismissRequest = {
            deleteHistoryDialog.value = false
        }, title = {
            Text(fontSize = 22.sp, text = language.value.deleteHistoryDialog)
        }, buttons = {
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.background(color = Color.DarkGray)
            ) {
                DialogButton(
                    onClick = { deleteHistoryDialog.value = false },
                    modifier = Modifier.weight(1F),
                    text = language.value.dialogDeleteHistoryDismiss
                )

                DialogButton(
                    onClick = {
                        deleteHistoryDialog.value = false
                        viewModel.resetHistory()
                    },
                    modifier = Modifier.weight(1F),
                    text = language.value.dialogDeleteHistoryAccept
                )
            }
        })
    }
}

@Composable
fun DialogButton(onClick: () -> Unit, modifier: Modifier, text: String) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            color = Color.White,
        )
    }
}

@Composable
fun DefaultCalcButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    background: Color,
    textColor: Color,
    enabled: Boolean,
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun PrimaryButton(modifier: Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.primaryText,
        enabled = enabled
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit
) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.secondaryText,
        enabled = enabled,
    )
}

@Composable
fun TertiaryButton(modifier: Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.tertiaryText,
        enabled = enabled,
    )
}

@Composable
fun AdditionalButton(
    modifier: Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit
) {
    DefaultCalcButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = colors.primaryButton,
        textColor = colors.additionalText,
        enabled = enabled,
    )
}