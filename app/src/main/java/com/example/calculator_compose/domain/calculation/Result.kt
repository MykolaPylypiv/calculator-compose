package com.example.calculator_compose.domain.calculation

import com.example.calculator_compose.app.Strings
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
                example.replace(component.pi, "3.14159265").replace(component.e, "2.71828183")
            val data = PresentationValues(calculation = finalExample, action = operation)
            val values = mapper.map(data)

            val strNumbers = values.numbers
            var action = values.action

            if (strNumbers.isEmpty()) {
                return "0"
            }

            if (finalExample.first().toString() == "-") {
                strNumbers[0] = (-strNumbers[0].toDouble()).toString()
                action.removeFirst()
            }

            if (action.isNotEmpty() && strNumbers.isNotEmpty()) {
                if (strNumbers[0] == "0" && action[0] == "!") {
                    strNumbers[0] = "1"
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

                return if (result.toString().last().toString() == "0" && result.toString()
                        .contains(".")
                ) {
                    result.roundToInt().toString()
                } else {
                    result.toString()
                }
            }

            while (!component.numbers.contains(
                    finalExample.last().toString()
                ) && finalExample.last().toString() != ")"
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
                    val index = exam.indexOf("(")
                    var index1 = exam.length

                    if (action.contains(")")) {
                        index1 = exam.indexOf(")")
                    }

                    val newExample = exam.split("").subList(index + 2, index1 + 1).joinToString("")

                    val newOperation = newExample.split(
                        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")", ".", " "
                    ).joinToString("")

                    if (action.reversed().indexOf("(") - action.reversed().indexOf(")") != 1) {
                        if (action.indexOf("(") != 0 && component.actionWithOneNumber.contains(
                                action[action.indexOf("(") - 1]
                            ) || component.actionWithOneNumber.contains(action[0])
                        ) {
                            numeric[action.indexOf("(") - 1] = renewal(
                                example = newExample, operation = newOperation, radians = radians
                            ).toDouble()

                        } else {
                            numeric[action.indexOf("(")] = renewal(
                                example = newExample, operation = newOperation, radians = radians
                            ).toDouble()
                        }
                    }

                    if (action.indexOf(")") == action.size || action.indexOf(")") == -1) {
                        action = (action.subList(0, action.indexOf("(")))
                        exam = exam.substring(0, exam.indexOf("("))
                    } else {
                        action = (action.subList(0, action.indexOf("(")) + action.subList(
                            action.indexOf(")") + 1, action.size
                        )).toMutableList()

                        exam = exam.substring(0, exam.indexOf("(")) + exam.substring(
                            exam.indexOf(")") + 1, exam.length
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

            return if (result.toString().last().toString() == "0" && result.toString()
                    .contains(".")
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