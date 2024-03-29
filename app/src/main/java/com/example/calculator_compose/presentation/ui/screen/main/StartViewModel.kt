package com.example.calculator_compose.presentation.ui.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.data.room.AppDatabase
import com.example.calculator_compose.domain.interactor.MainInteractor
import com.example.calculator_compose.domain.model.Languages
import com.example.calculator_compose.domain.model.PresentationValues
import com.example.calculator_compose.navigation.NavigationTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class StartViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val dispatcher: CoroutineContext,
    private val db: AppDatabase
) : ViewModel() {

    var example: MutableLiveData<String> = MutableLiveData(interactor.getCalculation().calculation)
    var result: MutableLiveData<String> = MutableLiveData(interactor.result())

    var history: Flow<String?> = loadHistory()

    private var action: String = interactor.getCalculation().action

    fun numberPress(text: String) {
        example.value = interactor.number(
            text = text, action = action, example = example.value.toString()
        )
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun zeroPress() {
        example.value = interactor.zero(example = example.value.toString())
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun comaPress() {
        example.value = interactor.coma(example = example.value.toString(), action = action)
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun actionPress(text: String) {
        val values = interactor.action(
            text = text, example = example.value.toString(), action = action
        )

        example.value = values.calculation
        action = values.action
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun equalPress() {
        val allValues = interactor.equal(
            example = example.value.toString(), operation = action, history = ""
        )

        example.value = allValues.calculation
        action = allValues.action

        viewModelScope.launch(dispatcher) {
            saveHistory(history.first() + allValues.history)
        }
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun exampleBack() {
        val values = interactor.back(example = example.value.toString(), action = action)

        example.value = values.calculation
        action = values.action
        result.value = interactor.renewal(example.value.toString(), action)
    }

    fun exampleClear() {
        example.value = Strings.START_EXAMPLE
        action = Strings.EMPTY
        result.value = Strings.NUMBER_ZERO
    }

    fun navigationToSettingsTheme(navController: NavController) {
        navController.navigate(NavigationTree.SettingsTheme.name)
    }

    fun navigationToAdditional(navController: NavController) {
        navController.navigate(NavigationTree.Additional.name)
        interactor.setCalculation(
            PresentationValues(
                calculation = example.value.toString(), action = action
            )
        )
    }

    fun resetHistory() {
        viewModelScope.launch(dispatcher) {
            saveHistory("")
        }
    }

    fun updateLanguage(value: Boolean) {
        val dao = db.languageDao()

        viewModelScope.launch(dispatcher) {
            dao.deleteAll()
            dao.insert(Languages(uid = 0, isEnglish = value))
        }
    }

    fun textButtonChangeLanguage(value: Boolean) = if (value) "ENG" else "UKR"

    private fun saveHistory(history: String) = viewModelScope.launch(dispatcher) {
        interactor.storeHistory().save(history)
    }

    private fun loadHistory() = interactor.storeHistory().get()
}