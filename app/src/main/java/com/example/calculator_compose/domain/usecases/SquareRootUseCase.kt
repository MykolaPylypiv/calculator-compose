package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface SquareRootUseCase {

    fun sqrt(example: String, action: String): PresentationValues

    class Base @Inject constructor() : SquareRootUseCase {
        val text = "√"

        override fun sqrt(example: String, action: String): PresentationValues {
            val xExample = example.last().toString()

            if (example == "null")
                return PresentationValues(text, action + text)

            if (xExample != "√" &&
                xExample != "1" &&
                xExample != "2" &&
                xExample != "3" &&
                xExample != "4" &&
                xExample != "5" &&
                xExample != "6" &&
                xExample != "7" &&
                xExample != "8" &&
                xExample != "9" &&
                xExample != "0" &&
                xExample != "!"
            )
                return PresentationValues(example + text, action + text)

            return PresentationValues(example, action)
        }
    }
}