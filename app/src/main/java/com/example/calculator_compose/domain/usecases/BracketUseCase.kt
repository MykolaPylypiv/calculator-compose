package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface BracketUseCase {

    fun leftBracket(example: String, action: String): PresentationValues

    fun rightBracket(example: String, action: String): PresentationValues

    fun setBracket(boolean: Boolean)

    class Base @Inject constructor() : BracketUseCase {
        private val textLeftBracket = "("
        private val textRightBracket = ")"

        override fun leftBracket(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()
            var fourLastString = ""

            if (example.length >= 4) {
                fourLastString = example.drop(example.length - 4)
            }

            if (example == "null") {
                return PresentationValues(calculation = textLeftBracket, action = textLeftBracket)
            }

            if (bracket &&
                lastString != "1" &&
                lastString != "2" &&
                lastString != "3" &&
                lastString != "4" &&
                lastString != "5" &&
                lastString != "6" &&
                lastString != "7" &&
                lastString != "8" &&
                lastString != "9" &&
                lastString != "0") {

                return PresentationValues(
                    calculation = example + textLeftBracket,
                    action = action + textLeftBracket
                )
            }

            return PresentationValues(calculation = example, action = action)
        }

        override fun rightBracket(example: String, action: String): PresentationValues {
            val lastString = example.last().toString()

            if (example == "null") {
                return PresentationValues(example, action)
            }

            if (!bracket &&
                lastString != "√" &&
                lastString != "!" &&
                lastString != "." &&
                lastString != "+" &&
                lastString != "-" &&
                lastString != "*" &&
                lastString != "/" &&
                lastString != "%" &&
                lastString != "^" &&
                lastString != ")" &&
                lastString != "("
            ) {
                return PresentationValues(example + textRightBracket, action + textRightBracket)
            }

            return PresentationValues(example, action)
        }

        override fun setBracket(boolean: Boolean) {
            bracket = boolean
        }

        private companion object {

            var bracket = true
        }
    }
}