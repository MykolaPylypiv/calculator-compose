package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface FactorialUseCase {

    fun factorial(example: String, action: String): PresentationValues

    class Base @Inject constructor() : FactorialUseCase {
        val text = "!"

        override fun factorial(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()
            var fourLastString = ""

            if (example.length >= 4) {
                fourLastString = example.drop(example.length - 4)
            }

            if (example == "null")
                return PresentationValues("0$text", action + text)

            if (lastString != "√" &&
                lastString != "!" &&
                lastString != "." &&
                lastString != "+" &&
                lastString != "-" &&
                lastString != "*" &&
                lastString != "/" &&
                lastString != "%" &&
                lastString != "^" &&
                fourLastString != "sin(" &&
                fourLastString != "cos(" &&
                fourLastString != "tan("
            )
                return PresentationValues(example + text, action + text)

            return PresentationValues(example, action)
        }

    }
}