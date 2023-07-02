package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface NumberUseCase {

    fun number(text: String, example: String, action: String): String

    class Base @Inject constructor() : NumberUseCase {

        private val zero = Strings.NUMBER_ZERO
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val factorial = Strings.ACTION_FACTORIAL
        private val empty = Strings.EMPTY
        private val pi = Strings.NUMBER_P
        private val rightBr = Strings.RIGHT_BRACKET

        override fun number(
            text: String, example: String, action: String
        ): String {
            if (example.last().toString() == pi) return example

            if (example == startExample) return text

            if (example.last().toString() == factorial) return example

            if (example.last().toString() != zero && example.last().toString() != rightBr) return example + text

            if (example.dropLast(n = 1) != empty && example[example.length - 2] != action.lastOrNull()) return example + text

            return example
        }

    }
}