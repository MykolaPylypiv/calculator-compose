package com.example.calculator_compose.domain.calculation.additional

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.model.DomainAllValues
import com.example.calculator_compose.domain.model.DomainValues
import javax.inject.Inject

interface EqualCheck {

    fun firstCheck(example: String): DomainAllValues

    fun initialCheck(values: DomainValues, example: String): DomainValues

    class Base @Inject constructor(
        private val calc: PrimitiveCalculation.Base, private val additional: EqualReturn.Base
    ) : EqualCheck {

        private val pi = Strings.NUMBER_P
        private val e = Strings.NUMBER_E
        private val zero = Strings.NUMBER_ZERO
        private val one = Strings.NUMBER_ONE
        private val factorial = Strings.ACTION_FACTORIAL
        private val minus = Strings.ACTION_MINUS

        override fun firstCheck(example: String): DomainAllValues {
            var result = 0.0

            when (example) {
                pi -> result = calc.numPi()
                e -> result = calc.numE()
                zero + factorial -> result = 1.0
            }

            return additional.equalReturn(result = result, example = example)
        }

        override fun initialCheck(values: DomainValues, example: String): DomainValues {
            val numbers = values.numbers
            val action = values.action
            val firstExample = example.first().toString()

            if (numbers[0] == zero && action[0] == factorial) {
                numbers[0] = one
                action.removeFirst()
            }

            if (firstExample == minus && numbers.count() != 1) {
                numbers[0] = (-(numbers[0]).toDouble()).toString()
                action.removeFirst()
            }

            return DomainValues(numbers = numbers, action = action)
        }
    }
}