package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface ComaUseCase {

    fun coma(example: String, action: String): String

    class Base @Inject constructor() : ComaUseCase {

        override fun coma(example: String, action: String): String {
            val numbers = example.split("+", "-", "*", "/", "%", "^").toMutableList()

            if (numbers.last().contains(".")) {
                return example
            }

            if (example != "null" && example.last().toString() != ".") {
                if (action.isNotEmpty() && example.last() != action.last())
                    return "$example."
                else if (action.isEmpty())
                    return "$example."
            }
            return example
        }
    }
}