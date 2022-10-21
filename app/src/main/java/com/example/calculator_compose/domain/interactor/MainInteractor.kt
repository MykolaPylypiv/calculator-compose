package com.example.calculator_compose.domain.interactor

import com.example.calculator_compose.core.Store
import com.example.calculator_compose.domain.CalculationRow
import com.example.calculator_compose.domain.model.AllValues
import com.example.calculator_compose.domain.model.Values
import com.example.calculator_compose.domain.usecases.*

interface MainInteractor : NumberUseCase, ZeroUseCase, ComaUseCase, ActionUseCase, EqualUseCase,
    BackUseCase, CalculationRow {

    fun storeHistory(): Store

    class Base(
        val number: NumberUseCase,
        val zero: ZeroUseCase,
        val coma: ComaUseCase,
        val action: ActionUseCase,
        val equal: EqualUseCase,
        val back: BackUseCase,
        val storeHistoryCalculation: Store,
        val calculationRow: CalculationRow
    ) : MainInteractor {

        override fun number(
            text: String, example: String, action: String
        ): String = number.number(text = text, example, action)

        override fun zero(example: String): String = zero.zero(example = example)

        override fun coma(example: String, action: String): String =
            coma.coma(example = example, action = action)

        override fun action(text: String, example: String, action: String): Values =
            this.action.action(text = text, example = example, action = action)

        override fun equal(example: String, action: String, history: String): AllValues =
            equal.equal(example = example, action = action, history = history)

        override fun back(example: String, action: String): Values =
            back.back(example = example, action = action)

        override fun getCalculation() = calculationRow.getCalculation()

        override fun setCalculation(value: Values) = calculationRow.setCalculation(value)

        override fun storeHistory(): Store = storeHistoryCalculation

    }
}