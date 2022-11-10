package com.example.calculator_compose.domain.calculation.calculation

import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface Calculation {

    fun calculation(
        text: MutableList<String>, value: DomainCalculationValues
    ): DomainCalculationValues

    class Base @Inject constructor(
        private val actionsEqualTo: ActionsEqualTo.Base,
    ) : Calculation {

        override fun calculation(
            text: MutableList<String>, value: DomainCalculationValues
        ): DomainCalculationValues {

            val action = value.action
            var result = value

            for (_text in text) {
                while (action.contains(_text)) {

                    when (_text) {
                        "^", "%", "*", "/" -> result =
                            actionsEqualTo.actionWithTwoNumbers(value = value, text = _text)

                        "sin", "cos", "tan","lg", "√", "!" -> result =
                            actionsEqualTo.actionWithOneNumbers(value = value, text = _text)
                    }
                }
            }

            return result
        }
    }
}