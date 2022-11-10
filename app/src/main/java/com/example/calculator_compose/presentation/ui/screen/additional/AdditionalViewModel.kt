package com.example.calculator_compose.presentation.ui.screen.additional

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.calculator_compose.domain.interactor.AdditionalInteractor
import com.example.calculator_compose.domain.model.PresentationValues
import com.example.calculator_compose.navigation.NavigationTree
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AdditionalViewModel @Inject constructor(
    private val interactor: AdditionalInteractor, private val dispatcher: CoroutineContext
) : ViewModel() {

    var example: MutableLiveData<String> = MutableLiveData(interactor.getCalculation().calculation)
    var history: Flow<String?> = loadHistory()
    private var action: String = interactor.getCalculation().action

    fun numberPress(text: String) {
        example.value = interactor.number(
            text = text, action = action, example = example.value.toString()
        )
    }

    fun letterNumPress(text: String) {
        example.value = interactor.letterNum(
            text = text, action = action, example = example.value.toString()
        )
    }

    fun zeroPress() {
        example.value = interactor.zero(example = example.value.toString())
    }

    fun comaPress() {
        example.value = interactor.coma(example = example.value.toString(), action = action)
    }

    fun actionPress(text: String) {
        val values =
            interactor.action(text = text, example = example.value.toString(), action = action)

        example.value = values.calculation
        action = values.action
    }

    fun squareRootPress() {
        val values = interactor.sqrt(example = example.value.toString(), action = action)

        example.value = values.calculation
        action = values.action
    }

    fun factorialPress() {
        val values = interactor.factorial(example = example.value.toString(), action = action)

        example.value = values.calculation
        action = values.action
    }

    fun trigonometricPress(text: String) {
        val values = interactor.trigonometric(
            text = text,
            example = example.value.toString(),
            action = action
        )

        example.value = values.calculation
        action = values.action
    }

    fun rightBracket() {
        val values = interactor.rightBracket(example = example.value.toString(), action = action)

        interactor.setBracket(true)
        example.value = values.calculation
        action = values.action
    }

    fun leftBracket() {
        val values = interactor.leftBracket(example = example.value.toString(), action = action)

        interactor.setBracket(false)
        example.value = values.calculation
        action = values.action
    }

    fun equalPress() {
        var newHistory = ""

        val allValues = interactor.equal(
            example = example.value.toString(), operation = action, history = newHistory
        )

        example.value = allValues.calculation
        action = allValues.action
        newHistory += allValues.history

        viewModelScope.launch(dispatcher) {
            saveHistory(history.first() + newHistory)
        }
    }

    fun exampleBack() {
        val values = interactor.back(example = example.value.toString(), action = action)

        example.value = values.calculation
        action = values.action
    }

    fun exampleClear() {
        example.value = "null"
        action = ""
    }

    fun navigationToAdditional(navController: NavController) {
        navController.navigate(NavigationTree.Main.name)
        interactor.setCalculation(
            PresentationValues(
                calculation = example.value.toString(), action = action
            )
        )
    }

    fun deg() {} //deg rad

    fun twoND() {} //2nd

    private fun saveHistory(history: String) = viewModelScope.launch(dispatcher) {
        interactor.storeHistory().save(history)
    }

    private fun loadHistory() = interactor.storeHistory().get()
}