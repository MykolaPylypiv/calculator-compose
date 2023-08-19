package com.example.calculator_compose.presentation.ui.screen.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
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
import com.example.calculator_compose.app.colors
import com.example.calculator_compose.app.language

@Composable
fun MainAppBar(viewModel: MainViewModel, navController: NavController, context: Context) {
    val changeLanguage = remember { mutableStateOf(viewModel.textChangeLanguage()) }
    val deleteHistoryDialog = remember { mutableStateOf(false) }

    TopAppBar(backgroundColor = colors.primaryBackground, elevation = 1.dp) {
        Spacer(modifier = Modifier.weight(1F))

        TextButton(
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

    if (deleteHistoryDialog.value) {
        AlertDialog(onDismissRequest = {
            deleteHistoryDialog.value = false
        }, title = {
            Text(fontSize = 22.sp, text = language.deleteHistoryDialog)
        }, buttons = {
            Row(
                modifier = Modifier.background(color = Color.DarkGray)
            ) {
                DialogButton(
                    onClick = { deleteHistoryDialog.value = false },
                    modifier = Modifier.weight(1F),
                    text = language.dialogDeleteHistoryDismiss
                )

                DialogButton(
                    onClick = {
                        deleteHistoryDialog.value = false
                        viewModel.resetHistory()
                    }, modifier = Modifier.weight(1F), text = language.dialogDeleteHistoryAccept
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
            color = colors.primaryText,
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