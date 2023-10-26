package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.ExampleComponent
import javax.inject.Inject
import kotlin.math.roundToInt

interface UtilResult {

    fun output(result: Double): String

    fun preparation(exam: String): String

    class Base @Inject constructor(
        private val component: ExampleComponent.Base,
    ) : UtilResult {
        /** return round result */

        override fun output(result: Double): String {
            val output = result.toString()
            return if (output.last().toString() == Strings.NUMBER_ZERO && output.contains(
                    Strings.POINT
                )
            ) {
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
                ) && example.last().toString() != Strings.RIGHT_BRACKET
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
    }
}
