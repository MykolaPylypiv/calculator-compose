package com.example.calculator_compose.domain.calculation.priority

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

        override fun orderCalculation(value: DomainCalculationValues): DomainCalculationValues {
            var result = priorityAction(value)
            result = highPriorityAction(result)
            result = secondPriorityAction(result)
            result = lowestPriorityAction(result)

            return result
        }

        override fun priorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val priorityAction = mutableListOf("!", "√")

            return calculation.calculation(priorityAction, value)
        }

        override fun highPriorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val brackets = listOf("(", ")")
            var action: MutableList<String> = value.action

            while (action.containsAll(brackets)) {
                val startIndex = value.action.indexOf("(")
                val endIndex = value.action.indexOf(")")

                val numbers =
                    if (!action.contains("sin") &&
                        !action.contains("cos") &&
                        !action.contains("tan") &&
                        !action.contains("lg") &&
                        !action.contains("ln")
                    ) {
                        value.numbers.subList(startIndex, endIndex)
                    } else value.numbers.subList(startIndex - 1, endIndex - 1)

                action = value.action.subList(startIndex + 1, endIndex)

                val result = orderCalculation(
                    DomainCalculationValues(action = action, numbers = numbers)
                )

                if (value.action.contains("sin") ||
                    value.action.contains("cos") ||
                    value.action.contains("tan") ||
                    value.action.contains("lg") ||
                    value.action.contains("ln")

                ) {
                    value.numbers[startIndex - 1] = result.numbers[0]
                } else value.numbers[startIndex] = result.numbers[0]

                value.action.removeAll(result.action + brackets)

                return value
            }

            return value
        }

        override fun secondPriorityAction(value: DomainCalculationValues): DomainCalculationValues {
            val secondPriorityAction = mutableListOf("sin", "cos", "tan", "lg", "ln", "^", "%", "*", "/")

            return calculation.calculation(secondPriorityAction, value)
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
