package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface NumberUseCase {

    fun number(text: String, example: String, action: String): String

    class Base @Inject constructor() : NumberUseCase {

        override fun number(
            text: String,
            example: String,
            action: String
        ): String {
            if (example == "null") {
                ComaUseCase.Base.comaEnabled = true
                return text
            }

            if (example.last().toString() != "0") {
                ComaUseCase.Base.comaEnabled = true
                return example + text
            }

            if (example.dropLast(1) != "" &&
                example[example.length - 2] != action.lastOrNull()
            ) {
                ComaUseCase.Base.comaEnabled = true
                return example + text
            }

            return example
        }
    }
}