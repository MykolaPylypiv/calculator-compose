package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface ComaUseCase {

    fun coma(example: String, action: String): String

    class Base @Inject constructor() : ComaUseCase {

        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION
        private val percent = Strings.ACTION_PERCENT
        private val point = Strings.POINT
        private val pow = Strings.ACTION_POW

        override fun coma(example: String, action: String): String {
            val numbers = example.split(
                plus, minus, multiply, division, percent, pow
            ).toMutableList()

            if (numbers.last().contains(other = point)) return example

            if (example.last().toString() != point) {
                if (action.isNotEmpty() && example.last() != action.last()) return "$example."
                else if (action.isEmpty()) return "$example."
            }

            return example
        }
    }
}