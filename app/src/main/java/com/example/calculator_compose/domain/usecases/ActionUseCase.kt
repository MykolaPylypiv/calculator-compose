package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.Values
import javax.inject.Inject

interface ActionUseCase {

    fun action(text: String, example: String, action: String): Values

    class Base @Inject constructor() : ActionUseCase {

        override fun action(text: String, example: String, action: String): Values {
            val xExample = example.last().toString()
            if (example != "null" &&
                xExample != "+" &&
                xExample != "-" &&
                xExample != "*" &&
                xExample != "/" &&
                xExample != "%" &&
                xExample != "." &&
                xExample != "^"
            ) return Values(example + text, action + text)

            return Values(example, action)
        }
    }
}