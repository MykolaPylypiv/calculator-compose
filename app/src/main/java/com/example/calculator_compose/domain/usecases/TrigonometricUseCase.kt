package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface TrigonometricUseCase {

    fun trigonometric(text: String, example: String, action: String): PresentationValues

    class Base @Inject constructor() : TrigonometricUseCase {

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
        private val rightBr = Strings.RIGHT_BRACKET
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val factorial = Strings.ACTION_FACTORIAL
        private val point = Strings.POINT

        override fun trigonometric(
            text: String, example: String, action: String
        ): PresentationValues {
            val lastString = example.last().toString()

            if (example == startExample) return PresentationValues(
                calculation = text, action = text
            )

            if (lastString != one && lastString != two && lastString != three && lastString != four && lastString != five && lastString != six && lastString != seven && lastString != eight && lastString != nine && lastString != zero && lastString != factorial && lastString != point && lastString != rightBr) return PresentationValues(
                calculation = example + text, action = action + text
            )

            return PresentationValues(calculation = example, action = action)
        }
    }

}
