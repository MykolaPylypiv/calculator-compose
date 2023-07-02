package com.example.calculator_compose.domain.calculation

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface Priority {

    fun firstPriority(num: Double, action: String): Double

    fun secondPriority(text: String, num: Double, isRadians: Boolean): Double

    fun thirdPriority(action: String, num: Double, num1: Double): Double

    fun lowestPriority(action: String, num: Double, num1: Double): Double

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
        private val component: ExampleComponent.Base,
    ): Priority {
        override fun firstPriority(num: Double, action: String): Double {
            when (action) {
                component.sqrt -> return primitiveCalculation.sqrt(num = num)

                component.factorial -> return primitiveCalculation.factorial(num = num.toInt()).toDouble()
            }
            throw IllegalArgumentException(Strings.EXCEPTION_FIRST_PRIORITY_NOT_HAVE_ACTION)
        }

        override fun secondPriority(text: String, num: Double, isRadians: Boolean): Double {
            when (text) {
                component.asin -> {
                    if (-1 < num && num < 1) {
                        return primitiveCalculation.arcSin(num = num)
                    }
                    return -1.0
                }

                component.acos -> {
                    if (-1 < num && num < 1) {
                        return primitiveCalculation.arcCos(num = num)
                    }
                    return -1.0
                }

                component.atan -> {
                    if (-1 < num && num < 1) {
                        return primitiveCalculation.arcTan(num = num)
                    }
                    return -1.0
                }

                component.sin -> return if (isRadians) primitiveCalculation.sin(num = num)
                else primitiveCalculation.sin(num = Math.toRadians(num))

                component.cos -> return if (isRadians) primitiveCalculation.cos(num = num)
                else primitiveCalculation.cos(num = Math.toRadians(num))

                component.tan -> return if (isRadians) primitiveCalculation.tan(num = num)
                else primitiveCalculation.tan(num = Math.toRadians(num))

                component.lg -> return primitiveCalculation.lg(num = num)

                component.ln -> return primitiveCalculation.ln(num = num)

                component.factorial -> return primitiveCalculation.factorial(num = num.toInt()).toDouble()

                component.sqrt -> return primitiveCalculation.sqrt(num = num)
            }
            throw IllegalArgumentException(Strings.EXCEPTION_SECOND_PRIORITY_NOT_HAVE_ACTION)
        }

        override fun thirdPriority(action: String, num: Double, num1: Double): Double {
            if (component.numbers.count() == 1) throw IllegalArgumentException(Strings.EXCEPTION_NUMBERS_COUNT_EQUAL_ONE)

            when (action) {
                component.pow -> return primitiveCalculation.power(num = num, num1 = num1)

                component.percent -> return primitiveCalculation.percent(num = num, num1 = num1)

                component.multiply -> return primitiveCalculation.multiply(num = num, num1 = num1)

                component.division -> return primitiveCalculation.division(num = num, num1 = num1)
            }
            throw IllegalArgumentException(Strings.EXCEPTION_THIRD_PRIORITY_NOT_HAVE_ACTION)
        }

        override fun lowestPriority(action: String, num: Double, num1: Double): Double {
            if (component.numbers.count() == 1) throw IllegalArgumentException(Strings.EXCEPTION_NUMBERS_COUNT_EQUAL_ONE)

            when (action) {
                component.plus -> return primitiveCalculation.plus(num = num, num1 = num1)

                component.minus -> return primitiveCalculation.minus(num = num, num1 = num1)
            }
            throw IllegalArgumentException(Strings.EXCEPTION_LOWEST_PRIORITY_NOT_HAVE_ACTION)
        }
    }
}