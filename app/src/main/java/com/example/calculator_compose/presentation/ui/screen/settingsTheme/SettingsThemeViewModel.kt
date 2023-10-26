package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.calculator_compose.app.Repository.variableTheme
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.VariableTheme
import com.example.calculator_compose.domain.repository.color.DeleteColor
import com.example.calculator_compose.domain.repository.color.InsertColor
import com.example.calculator_compose.domain.repository.variableTheme.DeleteVariableTheme
import com.example.calculator_compose.domain.repository.variableTheme.InsertVariableTheme
import com.example.calculator_compose.navigation.NavigationTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SettingsThemeViewModel @Inject constructor(
    private val insertColor: InsertColor.Base,
    private val deleteColor: DeleteColor.Base,
    private val insertTheme: InsertVariableTheme.Base,
    private val deleteTheme: DeleteVariableTheme.Base,
    private val dispatcher: CoroutineContext,
    db: AppDatabase
) : ViewModel() {

    private val colors = db.colorThemeDao().get()

    private val defaultColorTheme = ColorTheme(
        uid = 0,
        primaryText = 0xFF000000,
        secondaryText = 0xFFfe5e00,
        primaryButton = 0xFFFFFFFF,
        tertiaryText = 0xFF0591b4,
        additionalTextColor = 0xFF808080,
        primaryBackground = 0xFFFFFFFF,
    )

    var textColor = try {
        colors.primaryText
    } catch (e: NullPointerException) {
        defaultColorTheme.primaryText
    }

    var secondaryText = try {
        colors.secondaryText
    } catch (e: NullPointerException) {
        defaultColorTheme.secondaryText
    }

    var tertiaryText = try {
        colors.tertiaryText
    } catch (e: NullPointerException) {
        defaultColorTheme.tertiaryText
    }

    var historyText = try {
        colors.additionalTextColor
    } catch (e: NullPointerException) {
        defaultColorTheme.additionalTextColor
    }

    var primaryButton = try {
        colors.primaryButton
    } catch (e: NullPointerException) {
        defaultColorTheme.primaryButton
    }

    var backgroundColor = try {
        colors.primaryBackground
    } catch (e: NullPointerException) {
        defaultColorTheme.primaryBackground
    }

    fun selectTheme(theme: String, navController: NavController) {
        navController.navigate(NavigationTree.Start.name)

        viewModelScope.launch(dispatcher) {
            delay(250)

            deleteColor.deleteAll()
            variableTheme.value = theme
            deleteTheme.deleteAll()
            insertTheme.insert(VariableTheme(theme = theme))
        }
    }

    fun navigationToMain(navController: NavController) {
        navController.navigate(NavigationTree.Start.name)
    }

    fun updateColorTheme(palette: ColorTheme) {
        viewModelScope.launch(dispatcher) {
            deleteColor.deleteAll()

            insertColor.insert(palette)
        }
    }

    fun updateVariableTheme(theme: String) {
        viewModelScope.launch(dispatcher) {
            deleteTheme.deleteAll()

            insertTheme.insert(VariableTheme(theme = theme))
        }
    }

}