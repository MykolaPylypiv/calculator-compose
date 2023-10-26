package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.Strings.ACTION_FACTORIAL
import com.example.calculator_compose.app.Strings.ACTION_MINUS
import com.example.calculator_compose.app.Strings.NUMBER_ONE
import com.example.calculator_compose.app.Strings.NUMBER_ZERO
import com.example.calculator_compose.app.Strings.POINT
import com.example.calculator_compose.app.Strings.RIGHT_BRACKET
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.Priority
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import javax.inject.Inject
import kotlin.math.roundToInt

interface Result {

    fun result(): String

    fun renewal(example: String, radians: String = "deg"): String

    class Base @Inject constructor(
        private val mapper: MapperToDomainValues,
        private val component: ExampleComponent.Base,
        private val priority: Priority.Base,
        private val section: SelectSection.Base,
    ) : Result, UtilResult {
        override fun result() = result

        override fun renewal(example: String, radians: String): String {
            val values = mapper.map(example)

            val numbers = values.numbers
            val action = values.action

            val isRadians = radians != component.deg

            /** if numbers empty return */

            if (numbers.isEmpty()) {
                return NUMBER_ZERO
            }

            /** if action empty return */

            if (action.isEmpty()) {
                return numbers.first().toString()
            }

            if (component.actionWithThoNumber.contains(
                    example.last().toString()
                ) && numbers.size == 1
            ) {
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

            /** division on zero */

            if (example.contains("÷0")) {
                return Double.POSITIVE_INFINITY.toString()
            }

            /** if first number ends with action */

            val exam = preparation(exam = example)

            /** calculation */

            val result: Double = calculation(
                example = exam, isRadians = isRadians
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

        override fun preparation(exam: String): String {
            var example = exam
            while (!component.numbers.contains(
                    example.last().toString()
                ) && example.last().toString() != RIGHT_BRACKET
            ) {
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

            example = example.replace("0!", "1")

            return example
        }

        /** calculation */

        override fun calculation(example: String, isRadians: Boolean): Double {
            var result = example
            var resultData = mapper.map(result)

            while (resultData.action.any { component.action.contains(it) }) {
                val sectionExample = section.selectSection(result)
                val data = mapper.map(sectionExample)

                val action = data.action
                val numeric = data.numbers.map { it.toDouble() }.toMutableList()

                /** if first number starts with - */

                if (sectionExample.first().toString() == ACTION_MINUS) {
                    numeric[0] = -numeric[0]
                    action.removeFirst()
                }

                if (result == "5^-1.0")
                    throw IllegalArgumentException(numeric.toString() + action.toString())


                /** calculation */

                while (action.any { component.personal.contains(it) }) {
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
                }
                while (action.any { component.actionWithOneNumber.contains(it) }) {
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
                }
                while (action.any { component.second.contains(it) }) {
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
                }
                while (action.any { component.simple.contains(it) }) {
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

                result = if (result.contains(Strings.LEFT_BRACKET)) {
                    result.replace("($sectionExample)", numeric[0].toString())
                } else result.replace(sectionExample, numeric[0].toString())

                resultData = mapper.map(result)

                if (result.first().toString() == ACTION_MINUS && resultData.action.size == 1) break
            }

            return result.toDouble()
        }

    }
}

interface UtilResult {

    fun output(result: Double): String

    fun preparation(exam: String): String

    fun calculation(example: String, isRadians: Boolean): Double
}