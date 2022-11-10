package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface NumberEUseCase {

    fun e(example: String, action: String): String

    class Base @Inject constructor() : NumberEUseCase {

        private val text = "e"

        override fun e(
            example: String,
            action: String
        ): String {
            val lastString = example.last().toString()

            if (example == "null")
                return text

            if (lastString != "0" &&
                lastString != "1" &&
                lastString != "2" &&
                lastString != "3" &&
                lastString != "4" &&
                lastString != "5" &&
                lastString != "6" &&
                lastString != "7" &&
                lastString != "8" &&
                lastString != "9" &&
                lastString != "e" &&
                lastString != "π" &&
                lastString != ")"
            )
                return example + text

            if (example.dropLast(1) != "" &&
                example[example.length - 2] != action.lastOrNull()
            )
                return example + text

            return example
        }
    }
}