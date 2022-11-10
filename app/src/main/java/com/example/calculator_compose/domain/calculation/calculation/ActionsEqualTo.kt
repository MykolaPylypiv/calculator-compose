package com.example.calculator_compose.domain.calculation.calculation

import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface ActionsEqualTo {

    fun actionWithOneNumbers(
        value: DomainCalculationValues, text: String
    ): DomainCalculationValues

    fun actionWithTwoNumbers(
        value: DomainCalculationValues, text: String
    ): DomainCalculationValues

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
    ) : ActionsEqualTo {

        override fun actionWithOneNumbers(
            value: DomainCalculationValues, text: String
        ): DomainCalculationValues {
            val action = value.action
            val numbers = value.numbers

            val index = action.indexOf(text)
            val num = numbers[index]
            val availableActions = mutableListOf("sin", "cos", "tan", "lg", "ln", "!", "√")

            if (action[index] !in availableActions) throw IllegalArgumentException("action not equals action in function")

            when (text) {
                "sin" -> numbers[index] = primitiveCalculation.sin(num)

                "cos" -> numbers[index] = primitiveCalculation.cos(num)

                "tan" -> numbers[index] = primitiveCalculation.tan(num)

                "lg" -> numbers[index] = primitiveCalculation.lg(num)

                "ln" -> numbers[index] = primitiveCalculation.ln(num)

                "!" -> numbers[index] = primitiveCalculation.factorial(num.toInt()).toDouble()

                "√" -> numbers[index] = primitiveCalculation.sqrt(num)
            }
            action.removeAt(index)

            return DomainCalculationValues(numbers = numbers, action = action)
        }

        override fun actionWithTwoNumbers(
            value: DomainCalculationValues, text: String
        ): DomainCalculationValues {
            val action = value.action
            val numbers = value.numbers

            if (numbers.count() == 1) throw IllegalArgumentException("numbers count == 1 in action with two numbers")

            val index = action.indexOf(text)

            val num = numbers[index]
            val num1 = numbers[index + 1]

            val availableActions = mutableListOf("^", "%", "*", "/")

            if (action[index] !in availableActions) throw IllegalArgumentException("action not equals action in function")

            when (text) {
                "^" -> numbers[index + 1] = primitiveCalculation.power(num, num1)

                "%" -> numbers[index + 1] = primitiveCalculation.percent(num, num1)

                "*" -> numbers[index + 1] = primitiveCalculation.multiply(num, num1)

                "/" -> numbers[index + 1] = primitiveCalculation.division(num, num1)
            }
            numbers.removeAt(index)
            action.removeAt(index)

            return DomainCalculationValues(numbers = numbers, action = action)
        }

    }
}