package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.Strings.ACTION_FACTORIAL
import com.example.calculator_compose.app.Strings.ACTION_MINUS
import com.example.calculator_compose.app.Strings.EMPTY
import com.example.calculator_compose.app.Strings.LEFT_BRACKET
import com.example.calculator_compose.app.Strings.NUMBER_EIGHT
import com.example.calculator_compose.app.Strings.NUMBER_FIVE
import com.example.calculator_compose.app.Strings.NUMBER_FOUR
import com.example.calculator_compose.app.Strings.NUMBER_NINE
import com.example.calculator_compose.app.Strings.NUMBER_ONE
import com.example.calculator_compose.app.Strings.NUMBER_SEVEN
import com.example.calculator_compose.app.Strings.NUMBER_SIX
import com.example.calculator_compose.app.Strings.NUMBER_THREE
import com.example.calculator_compose.app.Strings.NUMBER_TWO
import com.example.calculator_compose.app.Strings.NUMBER_ZERO
import com.example.calculator_compose.app.Strings.POINT
import com.example.calculator_compose.app.Strings.RIGHT_BRACKET
import com.example.calculator_compose.app.Strings.SPACE
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.Priority
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlin.math.roundToInt

interface Result {

    fun result(): String

    fun renewal(example: String, operation: String, radians: String = "deg"): String

    class Base @Inject constructor(
        private val mapper: MapperToDomainValues,
        private val component: ExampleComponent.Base,
        private val priority: Priority.Base,
    ) : Result, UtilResult {
        override fun result() = result

        override fun renewal(example: String, operation: String, radians: String): String {
            val data = PresentationValues(calculation = example, action = operation)
            val values = mapper.map(data)

            val numbers = values.numbers
            var action = values.action

            val isRadians = radians != component.deg

            /** if numbers empty return */

            if (numbers.isEmpty()) {
                return NUMBER_ZERO
            }

            /** if action empty return */

            if (action.isEmpty()) {
                return numbers.first().toString()
            }

            if (component.actionWithThoNumber.contains(example.last().toString()) && numbers.size == 1) {
                return numbers.first().toString()
            }

            /** if example starts 0! */

            if (numbers[0] == NUMBER_ZERO && action[0] == ACTION_FACTORIAL) {
                numbers[0] = NUMBER_ONE
                action.removeFirst()

                if (action.size == 0) {
                    return NUMBER_ONE
                }
            }

            /** if example starts ( */

            if (numbers.size == 1 && action.first() == "(") {
                return numbers.first().toString()
            }

            /** if first number starts with - */

            if (example.first().toString() == ACTION_MINUS) {
                numbers[0] = (-numbers[0].toDouble()).toString()
                action.removeFirst()
            }

            /** if first number ends with action */

            val preparationValues = preparation(exam = data.calculation, action = action)

            val exam = preparationValues.example
            action = preparationValues.action

            /** calculation */

            val result: Double = calculation(
                CalculationValues(
                    action = action,
                    example = exam,
                    numbers = numbers,
                    radians = radians,
                    isRadians = isRadians
                )
            )

            /** return */

            return output(result = result)
        }

        private companion object {
            var result = Strings.START_EXAMPLE
        }

        /** return round result */

        override fun output(result: Double): String {
            val output = result.toString()
            return if (output.last().toString() == NUMBER_ZERO && output.contains(POINT)) {
                result.roundToInt().toString()
            } else {
                output
            }
        }

        /** if first number ends with action */

        override fun preparation(exam: String, action: MutableList<String>): PreparationValues {
            var example = exam
            while (!component.numbers.contains(
                    example.last().toString()
                ) && example.last().toString() != RIGHT_BRACKET
            ) {
                action.removeLast()

                /** if last action equal sin, cos, tg ect... */

                example = if (component.actionWithOneNumber.contains(
                        example.substring(
                            example.lastIndex - 2, example.lastIndex + 1
                        )
                    )
                ) {
                    example.removeRange(example.lastIndex - 2, example.lastIndex + 1)
                } else {
                    example.substring(0, example.lastIndex)
                }
            }

            return PreparationValues(action = action, example = example)
        }

        /** calculation */

        override fun calculation(values: CalculationValues): Double {
            var action = values.action
            var sectionExample = values.example

            val isRadians = values.isRadians
            val radians = values.radians

            val numeric = values.numbers.map { value ->
                value.toDouble()
            }.toMutableList()

            while (action.isNotEmpty()) {
                if (action.any { component.brackets.contains(it) }) {
                    val start = sectionExample.indexOf(LEFT_BRACKET)

                    val end = if (action.contains(RIGHT_BRACKET)) {
                        sectionExample.indexOf(RIGHT_BRACKET)
                    } else {
                        sectionExample.length
                    }

                    val newExample =
                        sectionExample.split("").subList(start + 2, end + 1).joinToString("")

                    val newAction = newExample.split(
                        NUMBER_ZERO,
                        NUMBER_ONE,
                        NUMBER_TWO,
                        NUMBER_THREE,
                        NUMBER_FOUR,
                        NUMBER_FIVE,
                        NUMBER_SIX,
                        NUMBER_SEVEN,
                        NUMBER_EIGHT,
                        NUMBER_NINE,
                        LEFT_BRACKET,
                        RIGHT_BRACKET,
                        POINT,
                        SPACE
                    )

                    val newOperation = newAction.joinToString(EMPTY)

                    if (action.indexOf(LEFT_BRACKET) != 0 && component.actionWithOneNumber.contains(
                            action[action.indexOf(LEFT_BRACKET) - 1]
                        ) || component.actionWithOneNumber.contains(action[0])
                    ) {
                        numeric[action.indexOf(LEFT_BRACKET) - 1] = renewal(
                            example = newExample, operation = newOperation, radians = radians
                        ).toDouble()

                    } else {
                        numeric[action.indexOf(LEFT_BRACKET)] = renewal(
                            example = newExample, operation = newOperation, radians = radians
                        ).toDouble()
                    }

                    if (action.indexOf(RIGHT_BRACKET) == action.size || !action.contains(
                            RIGHT_BRACKET
                        )
                    ) {
                        action = action.subList(0, action.indexOf(LEFT_BRACKET))
                        sectionExample =
                            sectionExample.substring(0, sectionExample.indexOf(LEFT_BRACKET))
                    } else {
                        action = (action.subList(0, action.indexOf(LEFT_BRACKET)) + action.subList(
                            action.indexOf(RIGHT_BRACKET) + 1, action.size
                        )).toMutableList()

                        sectionExample = sectionExample.substring(
                            0, sectionExample.indexOf(LEFT_BRACKET)
                        ) + sectionExample.substring(
                            sectionExample.indexOf(RIGHT_BRACKET) + 1, sectionExample.length
                        )
                    }

                    if (action.isEmpty()) {
                        break
                    }

                } else if (action.any { component.personal.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (component.personal.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]
                            numeric[index] = priority.firstPriority(num, item)

                            break
                        }
                    }
                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { component.actionWithOneNumber.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (component.actionWithOneNumber.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]

                            numeric[index] = priority.secondPriority(
                                num = num, text = item, isRadians = isRadians
                            )

                            break
                        }
                    }

                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { component.second.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (component.second.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]
                            val num1 = numeric[index + 1]

                            numeric[index + 1] =
                                priority.thirdPriority(action = item, num = num, num1 = num1)

                            break
                        }
                    }
                    numeric.removeAt(index)
                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { component.simple.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (component.simple.contains(item)) {
                            index = action.indexOf(item)

                            val num = numeric[index]
                            val num1 = numeric[index + 1]

                            numeric[index + 1] =
                                priority.lowestPriority(action = item, num = num, num1 = num1)

                            break
                        }
                    }

                    numeric.removeAt(index)
                    action.removeAt(index)
                }
            }

            return numeric[0]
        }
    }
}

interface UtilResult {

    fun output(result: Double): String

    fun preparation(exam: String, action: MutableList<String>): PreparationValues

    fun calculation(values: CalculationValues): Double
}