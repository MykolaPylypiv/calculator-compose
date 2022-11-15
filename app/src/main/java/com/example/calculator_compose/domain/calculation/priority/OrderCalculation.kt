package com.example.calculator_compose.domain.calculation.priority

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.Calculation
import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface OrderCalculation {

    fun orderCalculation(value: DomainCalculationValues): DomainCalculationValues

    fun priorityAction(value: DomainCalculationValues): DomainCalculationValues

    fun highPriorityAction(value: DomainCalculationValues): DomainCalculationValues

    fun secondPriorityAction(value: DomainCalculationValues): DomainCalculationValues

    fun lowestPriorityAction(value: DomainCalculationValues): DomainCalculationValues

    class Base @Inject constructor(
        private val lowestPriority: LowestPriorityAction.Base,
        private val calculation: Calculation.Base,
    ) : OrderCalculation {

        private val asin = Strings.ACTION_ARCSIN.dropLast(1)
        private val acos = Strings.ACTION_ARCCOS.dropLast(1)
        private val atan = Strings.ACTION_ARCTAN.dropLast(1)
        private val sin = Strings.ACTION_SIN.dropLast(1)
        private val cos = Strings.ACTION_COS.dropLast(1)
        private val tan = Strings.ACTION_TAN.dropLast(1)
        private val lg = Strings.ACTION_LG.dropLast(1)
        private val ln = Strings.ACTION_LN.dropLast(1)
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION
        private val percent = Strings.ACTION_PERCENT
        private val pow = Strings.ACTION_POW
        private val squareRoot = Strings.ACTION_SQUARE_ROOT
        private val factorial = Strings.ACTION_FACTORIAL
        private val leftBr = Strings.LEFT_BRACKET
        private val rightBr = Strings.RIGHT_BRACKET

        override fun orderCalculation(value: DomainCalculationValues): DomainCalculationValues {
            var result = priorityAction(value = value)
            result = highPriorityAction(value = result)
            result = secondPriorityAction(value = result)
            result = lowestPriorityAction(value = result)

            return result
        }

        override fun priorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val priorityAction = mutableListOf(factorial, squareRoot)

            return calculation.calculation(text = priorityAction, value = value)
        }

        override fun highPriorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val brackets = listOf(leftBr, rightBr)
            val trigonometric = listOf(
                asin, acos, atan, sin, cos, tan, lg, ln
            )

            var action: MutableList<String> = value.action

            while (action.contains("(")) {
                val startIndex = value.action.indexOf(element = leftBr)
                val endIndex = value.action.indexOf(element = rightBr)

                action = value.action.subList(fromIndex = startIndex + 1, toIndex = endIndex)

                val numbers = if (value.action.contains(
                        element = trigonometric[0]
                    ) || value.action.contains(
                        element = trigonometric[1]
                    ) || value.action.contains(
                        element = trigonometric[2]
                    ) || value.action.contains(
                        element = trigonometric[3]
                    ) || value.action.contains(
                        element = trigonometric[4]
                    ) || value.action.contains(
                        element = trigonometric[5]
                    ) || value.action.contains(
                        element = trigonometric[6]
                    ) || value.action.contains(
                        element = trigonometric[7]
                    )
                ) {
                    value.numbers.subList(fromIndex = startIndex - 1, toIndex = endIndex - 1)
                } else value.numbers.subList(fromIndex = startIndex, toIndex = endIndex)

                val result = orderCalculation(
                    value = DomainCalculationValues(action = action, numbers = numbers)
                )

                if (auditContainsAction(
                        action = value.action, containsAction = trigonometric
                    )
                ) value.numbers[startIndex - 1] = result.numbers[0]
                else value.numbers[startIndex] = result.numbers[0]

                value.action.removeAll(elements = result.action + brackets)

                return value
            }

            return value
        }

        private fun auditContainsAction(
            action: MutableList<String>, containsAction: List<String>
        ): Boolean {
            for (contains in containsAction) {
                if (action.contains(contains)) return true
            }

            return false
        }

        override fun secondPriorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val secondPriorityAction = mutableListOf(
                asin, acos, atan, sin, cos, tan, lg, ln, pow, percent, multiply, division
            )

            return calculation.calculation(text = secondPriorityAction, value = value)
        }

        override fun lowestPriorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val numbers = value.numbers
            val action = value.action

            while (action.isNotEmpty()) {
                val firstAction = action[0]
                val num = numbers[0]
                val num1 = numbers[1]

                numbers.removeFirst()
                action.removeFirst()

                numbers[0] = lowestPriority.lowestPriorityAction(
                    action = firstAction, num = num, num1 = num1
                )
            }

            return DomainCalculationValues(numbers = numbers, action = action)
        }
    }
}
