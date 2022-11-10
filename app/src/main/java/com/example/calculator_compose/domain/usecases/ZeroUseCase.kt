package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface ZeroUseCase {

    fun zero(example: String): String

    class Base @Inject constructor() : ZeroUseCase {

        override fun zero(example: String): String {
            val numbers = example.split("+", "-", "*", "/", "%", "^", "√")

            return if (example == "null")
                "0"
            else if (
                numbers.last().contains(".") ||
                numbers.last() != "0"
            )
                example + "0"
            else example
        }
    }
}