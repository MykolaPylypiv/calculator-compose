package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.AllValues
import javax.inject.Inject
import kotlin.math.pow

interface EqualUseCase {

    fun equal(example: String, action: String, history: String): AllValues

    class Base @Inject constructor() : EqualUseCase {

        override fun equal(example: String, action: String, history: String): AllValues {
            var count = 0
            var result = 0.0
            var xAction = action

            if (xAction != "" && example.last() != xAction.last() && example != "null") {
                val numbers = example.split("+", "-", "*", "/", "%", "^").toMutableList()

                while (xAction.isNotEmpty()) {
                    when (xAction.substring(0, 1)) {
                        "+" -> result = numbers[count].toDouble() + numbers[count + 1].toDouble()
                        "-" -> result = numbers[count].toDouble() - numbers[count + 1].toDouble()
                        "*" -> result = numbers[count].toDouble() * numbers[count + 1].toDouble()
                        "/" -> result = numbers[count].toDouble() / numbers[count + 1].toDouble()
                        "%" -> result = numbers[count].toDouble() % numbers[count + 1].toDouble()
                        "^" -> result = numbers[count].toDouble().pow(numbers[count + 1].toDouble())
                    }
                    xAction = xAction.substring(1)
                    numbers[count + 1] = result.toString()
                    count += 1
                }
                return if (result.toString()
                        .substring(result.toString().length - 2, result.toString().length) == ".0"
                ) AllValues(
                    calculation = result.toString().substring(0, result.toString().length - 2),
                    action = "",
                    history = "\n\n$example\n = $result"
                )
                else AllValues(
                    calculation = result.toString(),
                    action = "",
                    history = "\n\n$example\n = $result"
                )
            }
            return AllValues(calculation = example, action = action, history = history)
        }
    }
}