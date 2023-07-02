package com.example.calculator_compose.domain.calculation.priority

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.Calculation
import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface OrderCalculation {

    fun orderCalculation(
        value: DomainCalculationValues, isRadians: Boolean
    ): DomainCalculationValues

    fun priorityAction(value: DomainCalculationValues, isRadians: Boolean): DomainCalculationValues

    fun highPriorityAction(
        value: DomainCalculationValues, isRadians: Boolean
    ): DomainCalculationValues

    fun secondPriorityAction(
        value: DomainCalculationValues, isRadians: Boolean
    ): DomainCalculationValues

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

        override fun orderCalculation(
            value: DomainCalculationValues, isRadians: Boolean
        ): DomainCalculationValues {
            var result = priorityAction(value = value, isRadians = isRadians)
            result = highPriorityAction(value = result, isRadians = isRadians)
            result = secondPriorityAction(value = result, isRadians = isRadians)
            result = lowestPriorityAction(value = result)

            return result
        }

        override fun priorityAction(
            value: DomainCalculationValues, isRadians: Boolean
        ): DomainCalculationValues {
            val priorityAction = mutableListOf(factorial, squareRoot)

            return calculation.calculation(
                text = priorityAction, value = value, isRadians = isRadians
            )
        }

        override fun highPriorityAction(
            value: DomainCalculationValues, isRadians: Boolean
        ): DomainCalculationValues {
            val trigonometric = listOf(asin, acos, atan, sin, cos, tan, lg, ln)

            while (value.action.contains(leftBr)) {
                val startIndex = value.action.indexOf(element = leftBr)
                val endIndex = value.action.indexOf(element = rightBr)

                val action = value.action.subList(fromIndex = startIndex + 1, toIndex = endIndex)

                val numbers: MutableList<Double> = if (trigonometric.contains(value.action[0])) {
                    value.numbers.subList(
                        fromIndex = startIndex - 1, toIndex = endIndex - 1
                    )
                } else if (startIndex != 0 && trigonometric.contains(value.action[startIndex - 1])) {
                    value.numbers.subList(fromIndex = startIndex - 1, toIndex = endIndex - 1)
                } else value.numbers.subList(fromIndex = startIndex, toIndex = endIndex)

                val result = orderCalculation(
                    value = DomainCalculationValues(action = action, numbers = numbers),
                    isRadians = isRadians
                )

                if (auditContainsAction(
                        action = value.action, containsAction = trigonometric
                    )
                ) value.numbers[startIndex - 1] = result.numbers[0]
                else value.numbers[startIndex] = result.numbers[0]

                value.action.remove("(")
                value.action.remove(")")

                if (!value.action.contains(leftBr)) {
                    return value
                }

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

        override fun secondPriorityAction(
            value: DomainCalculationValues, isRadians: Boolean
        ): DomainCalculationValues {
            val secondPriorityAction = mutableListOf(
                asin, acos, atan, sin, cos, tan, lg, ln, pow, percent, multiply, division
            )

            return calculation.calculation(
                text = secondPriorityAction, value = value, isRadians = isRadians
            )
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
