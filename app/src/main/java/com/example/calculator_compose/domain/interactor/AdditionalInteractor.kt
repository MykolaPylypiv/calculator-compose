package com.example.calculator_compose.domain.interactor

import com.example.calculator_compose.core.Store
import com.example.calculator_compose.domain.ConstCalculationRow
import com.example.calculator_compose.domain.calculation.additional.Result
import com.example.calculator_compose.domain.model.PresentationValues
import com.example.calculator_compose.domain.usecases.*

interface AdditionalInteractor : NumberUseCase, ZeroUseCase, ComaUseCase, ActionUseCase,
    EqualUseCase, BackUseCase, SquareRootUseCase, FactorialUseCase, TrigonometricUseCase,
    AdditionalNumberUseCase, DegreesToRadians, TwoNDUseCase, ConstCalculationRow, BracketUseCase,
    Result {

    fun storeHistory(): Store

    class Base(
        val number: NumberUseCase,
        val zero: ZeroUseCase,
        val coma: ComaUseCase,
        val action: ActionUseCase,
        val equal: EqualUseCase,
        val back: BackUseCase,
        val sqrt: SquareRootUseCase,
        val factorial: FactorialUseCase,
        val trigonometric: TrigonometricUseCase,
        val bracket: BracketUseCase,
        val additionalNumber: AdditionalNumberUseCase,
        val degreesToRadians: DegreesToRadians,
        val twoND: TwoNDUseCase,
        val storeHistoryCalculation: Store,
        val constCalculationRow: ConstCalculationRow,
        val result: Result
    ) : AdditionalInteractor {

        override fun number(
            text: String, example: String, action: String
        ) = number.number(text = text, example = example, action = action)

        override fun zero(example: String) = zero.zero(example = example)

        override fun coma(example: String, action: String) =
            coma.coma(example = example, action = action)

        override fun action(text: String, example: String, action: String) =
            this.action.action(text = text, example = example, action = action)

        override fun equal(
            example: String, operation: String, history: String, isRadians: String
        ) = equal.equal(
            example = example, operation = operation, history = history, isRadians = isRadians
        )

        override fun back(example: String, action: String) =
            back.back(example = example, action = action)

        override fun sqrt(example: String, action: String) =
            sqrt.sqrt(example = example, action = action)

        override fun factorial(example: String, action: String) =
            factorial.factorial(example = example, action = action)

        override fun trigonometric(text: String, example: String, action: String) =
            trigonometric.trigonometric(text = text, example = example, action = action)

        override fun letterNum(text: String, example: String, action: String) =
            additionalNumber.letterNum(text = text, example = example, action = action)

        override fun converting() = degreesToRadians.converting()

        override fun change(boolean: Boolean) = twoND.change(boolean)

        override fun getCalculation() = constCalculationRow.getCalculation()

        override fun setCalculation(value: PresentationValues) =
            constCalculationRow.setCalculation(value = value)

        override fun leftBracket(example: String, action: String) =
            bracket.leftBracket(example = example, action = action)

        override fun rightBracket(example: String, action: String) =
            bracket.rightBracket(example = example, action = action)

        override fun result() = result.result()

        override fun renewal(example: String, operation: String, radians: String) =
            result.renewal(example, operation, radians)

        override fun storeHistory(): Store = storeHistoryCalculation
    }
}