package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface BracketUseCase {

    fun leftBracket(example: String, action: String): PresentationValues

    fun rightBracket(example: String, action: String): PresentationValues

    class Base @Inject constructor() : BracketUseCase {
        private val zero = Strings.NUMBER_ZERO
        private val one = Strings.NUMBER_ONE
        private val two = Strings.NUMBER_TWO
        private val three = Strings.NUMBER_THREE
        private val four = Strings.NUMBER_FOUR
        private val five = Strings.NUMBER_FIVE
        private val six = Strings.NUMBER_SIX
        private val seven = Strings.NUMBER_SEVEN
        private val eight = Strings.NUMBER_EIGHT
        private val nine = Strings.NUMBER_NINE
        private val leftBr = Strings.LEFT_BRACKET
        private val rightBr = Strings.RIGHT_BRACKET
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

        override fun leftBracket(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()

            if (example == startExample) {
                return PresentationValues(
                    calculation = leftBr, action = leftBr
                )
            }

            if (lastString != zero && lastString != one && lastString != two && lastString != three && lastString != four && lastString != five && lastString != six && lastString != seven && lastString != eight && lastString != nine) return PresentationValues(
                calculation = example + leftBr, action = action + leftBr
            )

            return PresentationValues(calculation = example, action = action)
        }

        override fun rightBracket(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()

            if (example == startExample) return PresentationValues(
                calculation = example, action = action
            )

            if (lastString != squareRoot && lastString != factorial && lastString != point && lastString != plus && lastString != minus && lastString != multiply && lastString != division && lastString != percent && lastString != pow && lastString != leftBr) return PresentationValues(
                calculation = example + rightBr, action = action + rightBr
            )

            return PresentationValues(calculation = example, action = action)
        }
    }
}