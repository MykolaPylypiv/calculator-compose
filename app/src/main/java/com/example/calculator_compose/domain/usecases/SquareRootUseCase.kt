package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface SquareRootUseCase {

    fun sqrt(example: String, action: String): PresentationValues

    class Base @Inject constructor() : SquareRootUseCase {

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
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val factorial = Strings.ACTION_FACTORIAL
        private val squareRoot = Strings.ACTION_SQUARE_ROOT

        override fun sqrt(example: String, action: String): PresentationValues {
            val xExample = example.last().toString()

            if (example == startExample) return PresentationValues(
                calculation = squareRoot, action = action + squareRoot
            )

            if (xExample != squareRoot && xExample != one && xExample != two && xExample != three && xExample != four && xExample != five && xExample != six && xExample != seven && xExample != eight && xExample != nine && xExample != zero && xExample != factorial) return PresentationValues(
                calculation = example + squareRoot, action = action + squareRoot
            )

            return PresentationValues(calculation = example, action = action)
        }
    }
}