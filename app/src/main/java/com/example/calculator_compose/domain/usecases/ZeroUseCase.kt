package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface ZeroUseCase {

    fun zero(example: String): String

    class Base @Inject constructor() : ZeroUseCase {

        private val zero = Strings.NUMBER_ZERO
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION
        private val percent = Strings.ACTION_PERCENT
        private val point = Strings.POINT
        private val pow = Strings.ACTION_POW
        private val squareRoot = Strings.ACTION_SQUARE_ROOT

        override fun zero(example: String): String {
            val numbers = example.split(
                plus, minus, multiply, division, percent, pow, squareRoot
            )

            return if (example == startExample) zero
            else if (numbers.last()
                    .contains(other = point) || numbers.last() != zero
            ) example + zero
            else example
        }
    }
}