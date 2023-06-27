package com.example.calculator_compose.domain.calculation.calculation

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.DomainCalculationValues
import javax.inject.Inject

interface Calculation {

    fun calculation(
        text: MutableList<String>, value: DomainCalculationValues, isRadians: Boolean
    ): DomainCalculationValues

    class Base @Inject constructor(
        private val actionsEqualTo: ActionsEqualTo.Base,
    ) : Calculation {

        private val pow = Strings.ACTION_POW
        private val percent = Strings.ACTION_PERCENT
        private val multiply = Strings.ACTION_MULTIPLY
        private val division = Strings.ACTION_DIVISION
        private val asin = Strings.ACTION_ARCSIN.dropLast(1)
        private val acos = Strings.ACTION_ARCCOS.dropLast(1)
        private val atan = Strings.ACTION_ARCTAN.dropLast(1)
        private val sin = Strings.ACTION_SIN.dropLast(1)
        private val cos = Strings.ACTION_COS.dropLast(1)
        private val tan = Strings.ACTION_TAN.dropLast(1)
        private val lg = Strings.ACTION_LG.dropLast(1)
        private val ln = Strings.ACTION_LN.dropLast(1)
        private val squareRoot = Strings.ACTION_SQUARE_ROOT
        private val factorial = Strings.ACTION_FACTORIAL

        override fun calculation(
            text: MutableList<String>, value: DomainCalculationValues, isRadians: Boolean
        ): DomainCalculationValues {

            val action = value.action
            var result = value

            for (_text in text) {
                while (action.contains(element = _text)) {
                    when (_text) {
                        pow, percent, multiply, division -> result =
                            actionsEqualTo.actionWithTwoNumbers(value = value, text = _text)

                        asin, acos, atan, sin, cos, tan, lg, ln, squareRoot, factorial -> result =
                            actionsEqualTo.actionWithOneNumbers(value = value, text = _text, isRadians = isRadians)
                    }
                }
            }

            return result
        }
    }
}