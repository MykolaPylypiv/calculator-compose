package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface TrigonometricUseCase {

    fun trigonometric(text: String, example: String, action: String): PresentationValues

    class Base @Inject constructor() : TrigonometricUseCase {

        override fun trigonometric(text: String, example: String, action: String): PresentationValues {
            val lastString = example.last().toString()

            if (example == "null")
                return PresentationValues(text, action + text.dropLast(1))

            if (lastString != "1" &&
                lastString != "2" &&
                lastString != "3" &&
                lastString != "4" &&
                lastString != "5" &&
                lastString != "6" &&
                lastString != "7" &&
                lastString != "8" &&
                lastString != "9" &&
                lastString != "0" &&
                lastString != "!" &&
                lastString != "." &&
                lastString != ")"
            ) {
                return PresentationValues(example + text, action + text.dropLast(1))
            }
            return PresentationValues(example, action)
        }
    }

}
