package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.Priority
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.model.CalculationValues
import javax.inject.Inject

interface Calculation {

    fun calculation(example: String, isRadians: Boolean): Double

    fun actionOneNumber(
        action: MutableList<String>,
        numeric: MutableList<Double>,
        isRadians: Boolean,
        actions: List<String>
    ): CalculationValues

    fun actionTwoNumber(
        action: MutableList<String>,
        numeric: MutableList<Double>,
        actions: List<String>
    ): CalculationValues

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
                var numeric = data.numbers.map { it.toDouble() }.toMutableList()

                /** if first number starts with - */

                if (sectionExample.first().toString() == Strings.ACTION_MINUS) {
                    numeric[0] = -numeric[0]
                    action.removeFirst()
                }

                /** calculation */

                var values = actionOneNumber(
                    numeric = numeric,
                    action = action,
                    actions = component.personal,
                    isRadians = isRadians
                )

                values = actionOneNumber(
                    numeric = values.numeric,
                    action = values.action,
                    actions = component.actionWithOneNumber,
                    isRadians = isRadians
                )

                values = actionTwoNumber(
                    numeric = values.numeric,
                    action = values.action,
                    actions = component.second,
                )

                values = actionTwoNumber(
                    numeric = values.numeric,
                    action = values.action,
                    actions = component.simple,
                )

                numeric = values.numeric

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

        override fun actionOneNumber(
            action: MutableList<String>,
            numeric: MutableList<Double>,
            isRadians: Boolean,
            actions: List<String>
        ): CalculationValues {
            while (action.any { actions.contains(it) }) {
                var index = 0

                for (item in action) {
                    if (actions.contains(item)) {
                        index = action.indexOf(item)
                        val num = numeric[index]

                        when (actions) {
                            component.personal -> numeric[index] = priority.firstPriority(num, item)
                            component.actionWithOneNumber -> numeric[index] =
                                priority.secondPriority(
                                    num = num, text = item, isRadians = isRadians
                                )
                        }

                        break
                    }
                }
                action.removeAt(index)

                if (action.isEmpty()) {
                    break
                }
            }

            return CalculationValues(numeric = numeric, action = action)
        }

        override fun actionTwoNumber(
            action: MutableList<String>,
            numeric: MutableList<Double>,
            actions: List<String>
        ): CalculationValues {

            while (action.any { actions.contains(it) }) {
                var index = 0

                for (item in action) {
                    if (actions.contains(item)) {
                        index = action.indexOf(item)
                        val num = numeric[index]
                        val num1 = numeric[index + 1]

                        when (actions) {
                            component.second -> numeric[index + 1] =
                                priority.thirdPriority(action = item, num = num, num1 = num1)
                            component.simple -> numeric[index + 1] =
                                priority.lowestPriority(action = item, num = num, num1 = num1)
                        }

                        break
                    }
                }

                numeric.removeAt(index)
                action.removeAt(index)

                if (action.isEmpty()) {
                    break
                }
            }

            return CalculationValues(numeric = numeric, action = action)
        }
    }
}