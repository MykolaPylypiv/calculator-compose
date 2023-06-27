package com.example.calculator_compose.domain.calculation.additional

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.model.DomainAllValues
import javax.inject.Inject
import kotlin.math.roundToInt

interface NumbersCountEqualOne {

    fun check(
        action: MutableList<String>,
        example: String,
        numbers: MutableList<String>,
        isRadians: String
    ): DomainAllValues

    class Base @Inject constructor(
        private val calc: PrimitiveCalculation.Base, private val additional: EqualReturn.Base
    ) : NumbersCountEqualOne {

        private val sin = Strings.ACTION_SIN.dropLast(1)
        private val cos = Strings.ACTION_COS.dropLast(1)
        private val tan = Strings.ACTION_TAN.dropLast(1)
        private val asin = Strings.ACTION_ARCSIN.dropLast(1)
        private val acos = Strings.ACTION_ARCCOS.dropLast(1)
        private val atan = Strings.ACTION_ARCTAN.dropLast(1)
        private val factorial = Strings.ACTION_FACTORIAL
        private val squareRoot = Strings.ACTION_SQUARE_ROOT
        private val lg = Strings.ACTION_LG.dropLast(1)
        private val ln = Strings.ACTION_LN.dropLast(1)
        private val minus = Strings.ACTION_MINUS
        private val deg = Strings.DEGREES

        override fun check(
            action: MutableList<String>,
            example: String,
            numbers: MutableList<String>,
            isRadians: String
        ): DomainAllValues {
            val num = numbers[0].toDouble()
            var result = numbers[0].toDouble()

            when (val text = action.first().toString()) {
                factorial -> result = calc.factorial(num.toInt()).toDouble()

                squareRoot -> result = calc.sqrt(num)

                sin, cos, tan -> result =
                    trigonometric(text = text, isRadians = isRadians, numbers = numbers)

                asin -> result = calc.arcSin(num)
                acos -> result = calc.arcCos(num)
                atan -> result = calc.arcTan(num)

                lg -> result = calc.lg(num)
                ln -> result = calc.ln(num)

                minus -> result = (-num)
            }

            return additional.equalReturn(result = result, example = example)
        }

        private fun trigonometric(
            text: String,
            isRadians: String,
            numbers: MutableList<String>,
        ): Double {

            return if (isRadians == deg) {
                val num = numbers[0].toDouble() * Math.PI / 180

                trigonometricCalculation(text = text, num = num)
            } else {
                val num = numbers[0].toDouble()

                trigonometricCalculation(text = text, num = num)
            }
        }

        private fun trigonometricCalculation(text: String, num: Double): Double {
            var result = num

            when (text) {
                sin -> result = calc.sin(num)
                cos -> result = calc.cos(num)
                tan -> result = calc.tan(num)
            }

            return result
        }
    }
}

//                sin -> result = ((calc.sin(num) * 100).roundToInt()).toDouble() / 100