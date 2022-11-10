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
            if (example.last().toString() == "π")
                return example

            if (example == "null")
                return text

            if (example.last().toString() == "!") {
                return example
            }

            if (example.last().toString() != "0")
                return example + text

            if (example.dropLast(1) != "" &&
                example[example.length - 2] != action.lastOrNull()
            )
                return example + text

            return example
        }

    }
}