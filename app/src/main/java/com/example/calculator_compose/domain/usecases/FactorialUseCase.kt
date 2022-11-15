package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface FactorialUseCase {

    fun factorial(example: String, action: String): PresentationValues

    class Base @Inject constructor() : FactorialUseCase {

        private val zero = Strings.NUMBER_ZERO
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val factorial = Strings.ACTION_FACTORIAL
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION
        private val percent = Strings.ACTION_PERCENT
        private val point = Strings.POINT
        private val pow = Strings.ACTION_POW
        private val squareRoot = Strings.ACTION_SQUARE_ROOT
        private val empty = Strings.EMPTY
        private val sin = Strings.ACTION_SIN
        private val cos = Strings.ACTION_COS
        private val tan = Strings.ACTION_TAN

        override fun factorial(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()
            var threeLastString = empty

            if (example.length >= 3) {
                threeLastString = example.drop(n = example.length - 3)
            }

            if (example == startExample) return PresentationValues(
                calculation = zero + factorial, action = action + factorial
            )

            if (lastString != squareRoot && lastString != factorial && lastString != point && lastString != plus && lastString != minus && lastString != multiply && lastString != division && lastString != percent && lastString != pow && threeLastString != sin && threeLastString != cos && threeLastString != tan) return PresentationValues(
                calculation = example + factorial, action = action + factorial
            )

            return PresentationValues(calculation = example, action = action)
        }

    }
}