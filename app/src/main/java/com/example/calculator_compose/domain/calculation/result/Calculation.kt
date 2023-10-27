package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.Priority
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import javax.inject.Inject

interface Calculation {

    fun calculation(example: String, isRadians: Boolean): Double

    class Base @Inject constructor(
        private val mapper: MapperToDomainValues,
        private val component: ExampleComponent.Base,
        private val priority: Priority.Base,
        private val section: SelectSection.Base,
    ) : Calculation {

        override fun calculation(example: String, isRadians: Boolean): Double {
            var result = example
            var resultData = mapper.map(result)

            while (resultData.action.any { component.action.contains(it) }) {
                var sectionExample = section.selectSection(result)
                val data = mapper.map(sectionExample)

                /** if example have ^(- */

                if (result.contains("^-")) {
                    var shift = 0

                    for (action in data.action) {
                        val index = data.action.indexOf(action)

                        if (data.action[index] == Strings.ACTION_MINUS && data.action[index - 1] == "^") {
                            data.action[index] = ""
                            data.numbers[index - shift] =
                                (-data.numbers[index - shift].toDouble()).toString()
                            shift += 1
                        }
                    }
                    data.action.removeIf { it == "" }

                    result = result.replace("^-", "^")
                    sectionExample = section.selectSection(result)
                }

                val action = data.action
                val numeric = data.numbers.map { it.toDouble() }.toMutableList()

                /** if first number starts with - */

                if (sectionExample.first().toString() == Strings.ACTION_MINUS) {
                    numeric[0] = -numeric[0]
                    action.removeFirst()
                }

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

                result =
                    if (result.contains(Strings.LEFT_BRACKET) && result.contains(Strings.RIGHT_BRACKET)) {
                        result.replace("($sectionExample)", numeric[0].toString())
                    } else if (result.contains(Strings.LEFT_BRACKET)) {
                        result.replace("($sectionExample", numeric[0].toString())
                    } else {
                        result.replace(sectionExample, numeric[0].toString())
                    }

                resultData = mapper.map(result)

                if (result.first()
                        .toString() == Strings.ACTION_MINUS && resultData.action.size == 1
                ) break
            }

            return result.toDouble()
        }
    }
}