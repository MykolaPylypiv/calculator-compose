package com.example.calculator_compose.domain.interactor

import com.example.calculator_compose.core.Store
import com.example.calculator_compose.domain.ConstCalculationRow
import com.example.calculator_compose.domain.calculation.result.Result
import com.example.calculator_compose.domain.model.PresentationValues
import com.example.calculator_compose.domain.usecases.*

interface MainInteractor : NumberUseCase, ZeroUseCase, ComaUseCase, ActionUseCase, EqualUseCase,
    BackUseCase, ConstCalculationRow, Result {

    fun storeHistory(): Store<String>

    class Base(
        val number: NumberUseCase,
        val zero: ZeroUseCase,
        val coma: ComaUseCase,
        val action: ActionUseCase,
        val equal: EqualUseCase,
        val back: BackUseCase,
        val storeHistoryCalculation: Store<String>,
        val constCalculationRow: ConstCalculationRow,
        val result: Result,
    ) : MainInteractor {

        override fun number(text: String, example: String, action: String) =
            number.number(text = text, example = example, action = action)

        override fun zero(example: String): String = zero.zero(example = example)

        override fun coma(example: String, action: String) =
            coma.coma(example = example, action = action)

        override fun action(text: String, example: String, action: String) =
            this.action.action(text = text, example = example, action = action)

        override fun equal(example: String, operation: String, history: String, isRadians: String) =
            equal.equal(example = example, operation = operation, history = history)

        override fun back(example: String, action: String) =
            back.back(example = example, action = action)

        override fun getCalculation() = constCalculationRow.getCalculation()

        override fun setCalculation(value: PresentationValues) =
            constCalculationRow.setCalculation(value = value)

        override fun result() = result.result()

        override fun renewal(example: String, radians: String) =
            result.renewal(example, radians)

        override fun storeHistory(): Store<String> = storeHistoryCalculation

    }
}