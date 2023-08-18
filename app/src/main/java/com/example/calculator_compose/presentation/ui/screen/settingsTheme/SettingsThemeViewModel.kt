package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.model.LightColorTheme
import com.example.calculator_compose.domain.repository.DeleteColor
import com.example.calculator_compose.domain.repository.InsertColor
import com.example.calculator_compose.navigation.NavigationTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsThemeViewModel @Inject constructor(
    private val insertColor: InsertColor.Base,
    private val deleteColor: DeleteColor.Base,
    db: AppDatabase
) : ViewModel() {

    private val colors = db.colorThemeDao().get()

    var textColor = try {
        colors.primaryText
    } catch (e: NullPointerException) {
        LightColorTheme.primaryText
    }

    var secondaryText = try {
        colors.secondaryText
    } catch (e: NullPointerException) {
        LightColorTheme.secondaryText
    }

    var tertiaryText = try {
        colors.tertiaryText
    } catch (e: NullPointerException) {
        LightColorTheme.tertiaryText
    }

    var additionalText = try {
        colors.historyColor
    } catch (e: NullPointerException) {
        LightColorTheme.historyColor
    }

    var primaryButton = try {
        colors.primaryButton
    } catch (e: NullPointerException) {
        LightColorTheme.primaryButton
    }

    var secondaryButton = try {
        colors.secondaryButton
    } catch (e: NullPointerException) {
        LightColorTheme.secondaryButton
    }

    var backgroundColor = try {
        colors.primaryBackground
    } catch (e: NullPointerException) {
        LightColorTheme.primaryBackground
    }

    fun navigationToMain(navController: NavController) {
        navController.navigate(NavigationTree.Main.name)
    }

    fun updateTheme(palette: ColorTheme) {
        viewModelScope.launch {
            deleteColor.deleteAll()

            insertColor.insert(palette)
        }
    }

}