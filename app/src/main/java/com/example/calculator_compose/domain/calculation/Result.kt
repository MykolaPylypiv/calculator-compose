package com.example.calculator_compose.domain.calculation

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.Strings.ACTION_FACTORIAL
import com.example.calculator_compose.app.Strings.ACTION_MINUS
import com.example.calculator_compose.app.Strings.CONST_NUMBER_E
import com.example.calculator_compose.app.Strings.CONST_NUMBER_PI
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
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject
import kotlin.math.roundToInt

interface Result {

    fun result(): String

    fun renewal(example: String, operation: String, radians: String = "deg"): String

    class Base @Inject constructor(
        private val mapper: MapperToDomainValues,
        private val component: ExampleComponent.Base,
        private val priority: Priority.Base,
    ) : Result {

        override fun result() = result

        override fun renewal(example: String, operation: String, radians: String): String {
            var finalExample =
                example.replace(component.pi, CONST_NUMBER_PI).replace(component.e, CONST_NUMBER_E)
            val data = PresentationValues(calculation = finalExample, action = operation)
            val values = mapper.map(data)

            val strNumbers = values.numbers
            var action = values.action

            if (strNumbers.isEmpty()) {
                return NUMBER_ZERO
            }

            if (finalExample.first().toString() == ACTION_MINUS) {
                strNumbers[0] = (-strNumbers[0].toDouble()).toString()
                action.removeFirst()
            }

            if (action.isNotEmpty() && strNumbers.isNotEmpty()) {
                if (strNumbers[0] == NUMBER_ZERO && action[0] == ACTION_FACTORIAL) {
                    strNumbers[0] = NUMBER_ONE
                    action.removeFirst()
                }
            }

            val isRadians = radians != component.deg
            val result: Double

            if (strNumbers.size == 0) {
                return ""
            }

            if (strNumbers.size == 1 && strNumbers.first() != "0.0") {
                action.removeAll(component.brackets)

                if (action.isEmpty() || !component.actionWithOneNumber.contains(action[0])) {
                    return ((strNumbers.first().toDouble()
                        .roundToInt() * 10000000) / 10000000).toString()
                }

                val num = strNumbers.first().toDouble()
                val text = action.first()

                result = priority.secondPriority(text, num, isRadians)

                return if (result.toString().last().toString() == NUMBER_ZERO && result.toString()
                        .contains(POINT)
                ) {
                    result.roundToInt().toString()
                } else {
                    result.toString()
                }
            }

            while (!component.numbers.contains(
                    finalExample.last().toString()
                ) && finalExample.last().toString() != RIGHT_BRACKET
            ) {
                action.removeLast()

                finalExample = if (!component.numbers.contains(
                        finalExample.last().toString()
                    ) && component.actionWithOneNumber.contains(
                        finalExample.substring(
                            finalExample.lastIndex - 2, finalExample.lastIndex + 1
                        )
                    )
                ) {
                    finalExample.removeRange(finalExample.lastIndex - 2, finalExample.length)
                } else {
                    finalExample.substring(0, finalExample.lastIndex)
                }
            }

            val numeric = strNumbers.map { value ->
                value.toDouble()
            }.toMutableList()

            var exam = finalExample

            while (action.isNotEmpty()) {
                if (action.any { component.brackets.contains(it) }) {
                    val index = exam.indexOf(LEFT_BRACKET)
                    var index1 = exam.length

                    if (action.contains(RIGHT_BRACKET)) {
                        index1 = exam.indexOf(RIGHT_BRACKET)
                    }

                    val newExample = exam.split("").subList(index + 2, index1 + 1).joinToString("")

                    val newOperation = newExample.split(
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
                    ).joinToString(EMPTY)

                    if (action.reversed().indexOf(LEFT_BRACKET) - action.reversed().indexOf(
                            RIGHT_BRACKET
                        ) != 1
                    ) {
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
                    }

                    if (action.indexOf(RIGHT_BRACKET) == action.size || action.indexOf(RIGHT_BRACKET) == -1) {
                        action = (action.subList(0, action.indexOf(LEFT_BRACKET)))
                        exam = exam.substring(0, exam.indexOf(LEFT_BRACKET))
                    } else {
                        action = (action.subList(0, action.indexOf(LEFT_BRACKET)) + action.subList(
                            action.indexOf(RIGHT_BRACKET) + 1, action.size
                        )).toMutableList()

                        exam = exam.substring(0, exam.indexOf(LEFT_BRACKET)) + exam.substring(
                            exam.indexOf(RIGHT_BRACKET) + 1, exam.length
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

            result = numeric[0]

            return if (result.toString().last().toString() == NUMBER_ZERO && result.toString()
                    .contains(POINT)
            ) {
                result.roundToInt().toString()
            } else {
                result.toString()
            }
        }

        private companion object {
            var result = Strings.START_EXAMPLE
        }
    }
}