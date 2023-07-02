package com.example.calculator_compose.domain.calculation.additional

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject
import kotlin.math.roundToInt

interface Result {

    fun result(): String

    fun renewal(example: String, operation: String, radians: String = "deg"): String

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
        private val mapper: MapperToDomainValues,
    ) : Result, ExampleComponent {

        private val deg = Strings.DEGREES

        override fun result() = result

        override fun renewal(example: String, operation: String, radians: String): String {
            var finalExample = example.replace("π", "3.14159265").replace("e", "2.71828183")
            val data = PresentationValues(calculation = finalExample, action = operation)
            val values = mapper.map(data)

            val _numbers = values.numbers
            var action = values.action

            if (_numbers.isEmpty()) {
                return "0"
            }

            if (finalExample.first().toString() == "-") {
                _numbers[0] = (-_numbers[0].toDouble()).toString()
                action.removeFirst()
            }

            if (action.isNotEmpty() && _numbers.isNotEmpty()) {
                if (_numbers[0] == "0" && action[0] == "!") {
                    _numbers[0] = "1"
                    action.removeFirst()
                }
            }

            val isRadians = radians != deg

            val result: Double

            if (_numbers.size == 0) {
                return ""
            }

            if (_numbers.size == 1 && _numbers.first() != "0.0") {
                action.removeAll(brackets)

                if (action.isEmpty() || !actionWithOneNumber.contains(action[0])) {
                    return ((_numbers.first().toDouble()
                        .roundToInt() * 10000000) / 10000000).toString()
                }

                val num = _numbers.first().toDouble()
                val text = action.first()

                result = secondPriority(text, num, isRadians)

                return if (result.toString().last().toString() == "0" && result.toString()
                        .contains(".")
                ) {
                    result.roundToInt().toString()
                } else {
                    result.toString()
                }
            }

            while (!numbers.contains(finalExample.last().toString()) && finalExample.last()
                    .toString() != ")"
            ) {
                action.removeLast()

                if (!numbers.contains(
                        finalExample.last().toString()
                    ) && actionWithOneNumber.contains(
                        finalExample.substring(
                            finalExample.lastIndex - 2, finalExample.lastIndex + 1
                        )
                    )
                ) {
                    finalExample =
                        finalExample.removeRange(finalExample.lastIndex - 2, finalExample.length)
                } else {
                    finalExample = finalExample.substring(0, finalExample.lastIndex)
                }
            }

            val numeric = _numbers.map { value ->
                value.toDouble()
            }.toMutableList()

            var exam = finalExample

            while (action.isNotEmpty()) {
                if (action.any { brackets.contains(it) }) {
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
                        if (action.indexOf("(") != 0 && actionWithOneNumber.contains(
                                action[action.indexOf("(") - 1]
                            ) || actionWithOneNumber.contains(action[0])
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

                } else if (action.any { personal.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (personal.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]
                            numeric[index] = firstPriority(num, item)

                            break
                        }
                    }
                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { actionWithOneNumber.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (actionWithOneNumber.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]

                            numeric[index] =
                                secondPriority(num = num, text = item, isRadians = isRadians)

                            break
                        }
                    }

                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { second.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (second.contains(item)) {
                            index = action.indexOf(item)
                            val num = numeric[index]
                            val num1 = numeric[index + 1]

                            numeric[index + 1] =
                                thirdPriority(action = item, num = num, num1 = num1)

                            break
                        }
                    }
                    numeric.removeAt(index)
                    action.removeAt(index)

                    if (action.isEmpty()) {
                        break
                    }
                } else if (action.any { simple.contains(it) }) {
                    var index = 0

                    for (item in action) {
                        if (simple.contains(item)) {
                            index = action.indexOf(item)

                            val num = numeric[index]
                            val num1 = numeric[index + 1]

                            numeric[index + 1] =
                                lowestPriority(action = item, num = num, num1 = num1)

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

        private fun firstPriority(num: Double, action: String): Double {
            when (action) {
                "√" -> return primitiveCalculation.sqrt(num = num)

                "!" -> return primitiveCalculation.factorial(num = num.toInt()).toDouble()
            }
            throw IllegalArgumentException("third priority not have action")
        }

        private fun secondPriority(
            text: String, num: Double, isRadians: Boolean
        ): Double {
            when (text) {
                "asin" -> return primitiveCalculation.arcSin(num = num)

                "acos" -> return primitiveCalculation.arcCos(num = num)

                "atan" -> return primitiveCalculation.arcTan(num = num)

                "sin" -> return if (isRadians) primitiveCalculation.sin(num = num)
                else primitiveCalculation.sin(num = Math.toRadians(num))

                "cos" -> return if (isRadians) primitiveCalculation.cos(num = num)
                else primitiveCalculation.cos(num = Math.toRadians(num))

                "tan" -> return if (isRadians) primitiveCalculation.tan(num = num)
                else primitiveCalculation.tan(num = Math.toRadians(num))

                "lg" -> return primitiveCalculation.lg(num = num)

                "ln" -> return primitiveCalculation.ln(num = num)

                "!" -> return primitiveCalculation.factorial(num = num.toInt()).toDouble()

                "√" -> return primitiveCalculation.sqrt(num = num)
            }
            throw IllegalArgumentException("calculation with one number not have this action")
        }

        private fun thirdPriority(action: String, num: Double, num1: Double): Double {
            if (numbers.count() == 1) throw IllegalArgumentException(Strings.EXCEPTION_NUMBERS_COUNT_EQUAL_ONE)

            when (action) {
                "^" -> return primitiveCalculation.power(num = num, num1 = num1)

                "%" -> return primitiveCalculation.percent(num = num, num1 = num1)

                "*" -> return primitiveCalculation.multiply(num = num, num1 = num1)

                "/" -> return primitiveCalculation.division(num = num, num1 = num1)
            }
            throw IllegalArgumentException("third priority not have action")
        }

        private fun lowestPriority(action: String, num: Double, num1: Double): Double {
            if (numbers.count() == 1) throw IllegalArgumentException(Strings.EXCEPTION_NUMBERS_COUNT_EQUAL_ONE)

            when (action) {
                "+" -> return primitiveCalculation.plus(num = num, num1 = num1)

                "-" -> return primitiveCalculation.minus(num = num, num1 = num1)
            }
            throw IllegalArgumentException("third priority not have action")
        }

        private companion object {
            var result = Strings.START_EXAMPLE
        }

        override val simple = mutableListOf("+", "-")

        override val second = mutableListOf("*", "/", "%", "^")

        override val personal = mutableListOf("√", "!")

        override val trigonometric = mutableListOf("sin", "cos", "tan", "acos", "asin", "atan")

        override val logarithms = mutableListOf("lg", "ln")

        override val brackets = mutableListOf("(", ")")

        override val numbers = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

        override val action =
            (simple + second + personal + trigonometric + logarithms + brackets).toMutableList()

        override val actionWithOneNumber = (personal + trigonometric + logarithms).toMutableList()
    }
}

interface ExampleComponent {
    val numbers: MutableList<String>

    val action: MutableList<String>

    val actionWithOneNumber: MutableList<String>

    val simple: MutableList<String>

    val second: MutableList<String>

    val personal: MutableList<String>

    val trigonometric: MutableList<String>

    val logarithms: MutableList<String>

    val brackets: MutableList<String>
}