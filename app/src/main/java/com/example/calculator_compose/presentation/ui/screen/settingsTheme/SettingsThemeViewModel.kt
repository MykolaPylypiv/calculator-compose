package com.example.calculator_compose.presentation.ui.screen.settingsTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.calculator_compose.domain.model.ColorTheme
import com.example.calculator_compose.domain.repository.DeleteColor
import com.example.calculator_compose.domain.repository.InsertColor
import com.example.calculator_compose.navigation.NavigationTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsThemeViewModel @Inject constructor(
    private val insertColor: InsertColor.Base, private val deleteColor: DeleteColor.Base
) : ViewModel() {

    fun navigationToMain(navController: NavController) {
        navController.navigate(NavigationTree.Main.name)
    }

    fun updateTheme(palette: ColorTheme) {
        viewModelScope.launch {
            deleteColor.deleteAll()

            insertColor.insert(palette)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            deleteColor.deleteAll()
        }
    }
}