package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.app.Strings.ACTION_FACTORIAL
import com.example.calculator_compose.app.Strings.NUMBER_ONE
import com.example.calculator_compose.app.Strings.NUMBER_ZERO
import com.example.calculator_compose.app.Strings.POINT
import com.example.calculator_compose.app.Strings.RIGHT_BRACKET
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import javax.inject.Inject
import kotlin.math.roundToInt

interface Result {

    fun result(): String

    fun renewal(example: String, radians: String = "deg"): String

    class Base @Inject constructor(
        private val mapper: MapperToDomainValues,
        private val component: ExampleComponent.Base,
        private val calculation: Calculation.Base,
        private val util: UtilResult.Base,
    ) : Result {
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

            val exam = util.preparation(exam = example)

            /** calculation */

            val result: Double = calculation.calculation(
                example = exam, isRadians = isRadians
            )

            /** return */

            return util.output(result = result)
        }

        private companion object {
            var result = Strings.START_EXAMPLE
        }

    }
}