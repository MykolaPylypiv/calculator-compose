package com.example.calculator_compose.domain.calculation.additional

import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.model.DomainAllValues
import com.example.calculator_compose.domain.model.DomainValues
import javax.inject.Inject

interface EqualCheck {

    fun firstCheck(example: String): DomainAllValues

    fun secondCheck(
        action: MutableList<String>, example: String, numbers: MutableList<String>
    ): DomainAllValues

    fun initialCheck(values: DomainValues, example: String): DomainValues

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
        private val additional: EqualReturn.Base
    ) : EqualCheck {

        override fun firstCheck(example: String): DomainAllValues {
            var result = 0.0

            when (example) {
                "π" -> result = primitiveCalculation.numPi()
                "e" -> result = primitiveCalculation.numE()
                "0!" -> result = 1.0
            }

            return additional.equalReturn(result = result, example = example)
        }

        override fun secondCheck(
            action: MutableList<String>, example: String, numbers: MutableList<String>
        ): DomainAllValues {
            var result = 0.0

            when (action.first().toString()) {
                "!" -> result = primitiveCalculation.factorial(numbers[0].toInt()).toDouble()
                "√" -> result = primitiveCalculation.sqrt(numbers[0].toDouble())
                "sin" -> result = primitiveCalculation.sin(numbers[0].toDouble())
                "cos" -> result = primitiveCalculation.cos(numbers[0].toDouble())
                "tan" -> result = primitiveCalculation.tan(numbers[0].toDouble())
                "lg" -> result = primitiveCalculation.lg(numbers[0].toDouble())
                "ln" -> result = primitiveCalculation.ln(numbers[0].toDouble())
                "-" -> result = (-numbers[0].toDouble())
            }

            return additional.equalReturn(result = result, example = example)
        }

        override fun initialCheck(values: DomainValues, example: String): DomainValues {
            val numbers = values.numbers
            val action = values.action
            val firstExample = example.first().toString()

            if (numbers[0] == "0" && action[0] == "!") {
                numbers[0] = "1"
                action.removeFirst()
            }

            if (firstExample == "-" && numbers.count() != 1) {
                numbers[0] = (-(numbers[0]).toDouble()).toString()
                action.removeFirst()
            }

            return DomainValues(numbers = numbers, action = action)
        }
    }
}