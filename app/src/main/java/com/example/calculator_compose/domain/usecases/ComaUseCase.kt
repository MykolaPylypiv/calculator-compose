package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface ComaUseCase {

    fun coma(example: String, action: String): String

    class Base @Inject constructor() : ComaUseCase {

        override fun coma(example: String, action: String): String {

            if (example != "null" && example.last().toString() != ".") {
                if (action.isNotEmpty() && example.last() != action.last()) {
                    comaEnabled = false
                    return "$example."
                } else if (action.isEmpty()) {
                    comaEnabled = false
                    return "$example."
                }
            }
            return example
        }

        companion object {

            var comaEnabled: Boolean = false
        }
    }
}