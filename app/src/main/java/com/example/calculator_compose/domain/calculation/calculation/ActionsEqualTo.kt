package com.example.calculator_compose.domain.calculation.calculation

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface ActionsEqualTo {

    fun actionWithOneNumbers(
        value: DomainCalculationValues, text: String, isRadians: Boolean
    ): DomainCalculationValues

    fun actionWithTwoNumbers(
        value: DomainCalculationValues, text: String
    ): DomainCalculationValues

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
    ) : ActionsEqualTo {

        private val asin = Strings.ACTION_ARCSIN.dropLast(1)
        private val acos = Strings.ACTION_ARCCOS.dropLast(1)
        private val atan = Strings.ACTION_ARCTAN.dropLast(1)
        private val sin = Strings.ACTION_SIN.dropLast(1)
        private val cos = Strings.ACTION_COS.dropLast(1)
        private val tan = Strings.ACTION_TAN.dropLast(1)
        private val lg = Strings.ACTION_LG.dropLast(1)
        private val ln = Strings.ACTION_LN.dropLast(1)
        private val factorial = Strings.ACTION_FACTORIAL
        private val squareRoot = Strings.ACTION_SQUARE_ROOT
        private val pow = Strings.ACTION_POW
        private val percent = Strings.ACTION_PERCENT
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION

        override fun actionWithOneNumbers(
            value: DomainCalculationValues, text: String, isRadians: Boolean
        ): DomainCalculationValues {
            val action = value.action
            val numbers = value.numbers

            val index = action.indexOf(element = text)
            val num = numbers[index]
            val availableActions = mutableListOf(
                asin, acos, atan, sin, cos, tan, lg, ln, factorial, squareRoot,
            )

            if (action[index] !in availableActions) throw IllegalArgumentException(Strings.EXCEPTION_ACTION_NOT_EQUALS)

            when (text) {
                asin -> numbers[index] = primitiveCalculation.arcSin(num = num)

                acos -> numbers[index] = primitiveCalculation.arcCos(num = num)

                atan -> numbers[index] = primitiveCalculation.arcTan(num = num)

                sin -> {
                    numbers[index] = if (isRadians) primitiveCalculation.sin(num = num)
                    else  primitiveCalculation.sin(num = Math.toRadians(num))
                }

                cos -> {
                    numbers[index] = if (isRadians) primitiveCalculation.cos(num = num)
                    else primitiveCalculation.cos(num = Math.toRadians(num))
                }

                tan -> {
                    numbers[index] = if (isRadians) primitiveCalculation.tan(num = num)
                    else primitiveCalculation.tan(num = Math.toRadians(num))
                }

                lg -> numbers[index] = primitiveCalculation.lg(num = num)

                ln -> numbers[index] = primitiveCalculation.ln(num = num)

                factorial -> numbers[index] =
                    primitiveCalculation.factorial(num = num.toInt()).toDouble()

                squareRoot -> numbers[index] = primitiveCalculation.sqrt(num = num)
            }
            action.removeAt(index = index)

            return DomainCalculationValues(numbers = numbers, action = action)
        }

        override fun actionWithTwoNumbers(
            value: DomainCalculationValues, text: String
        ): DomainCalculationValues {
            val action = value.action
            val numbers = value.numbers

            if (numbers.count() == 1) throw IllegalArgumentException(Strings.EXCEPTION_NUMBERS_COUNT_EQUAL_ONE)

            val index = action.indexOf(element = text)

            val num = numbers[index]
            val num1 = numbers[index + 1]

            val availableActions = mutableListOf(pow, percent, multiply, division)

            if (action[index] !in availableActions) throw IllegalArgumentException(Strings.EXCEPTION_ACTION_NOT_EQUALS)

            when (text) {
                pow -> numbers[index + 1] = primitiveCalculation.power(num = num, num1 = num1)

                percent -> numbers[index + 1] = primitiveCalculation.percent(num = num, num1 = num1)

                multiply -> numbers[index + 1] =
                    primitiveCalculation.multiply(num = num, num1 = num1)

                division -> numbers[index + 1] =
                    primitiveCalculation.division(num = num, num1 = num1)
            }
            numbers.removeAt(index = index)
            action.removeAt(index = index)

            return DomainCalculationValues(numbers = numbers, action = action)
        }

    }
}