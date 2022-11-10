package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface AdditionalNumberUseCase {

    fun letterNum(text: String, example: String, action: String): String

    class AdditionalNumber @Inject constructor() : AdditionalNumberUseCase {
        
        override fun letterNum(
            text: String,
            example: String,
            action: String
        ): String {
            val xExample = example.last().toString()

            if (example == "null")
                return text

            if (xExample != "0" &&
                xExample != "1" &&
                xExample != "2" &&
                xExample != "3" &&
                xExample != "4" &&
                xExample != "5" &&
                xExample != "6" &&
                xExample != "7" &&
                xExample != "8" &&
                xExample != "9" &&
                xExample != "e" &&
                xExample != "π"
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