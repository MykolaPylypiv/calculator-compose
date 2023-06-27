package com.example.calculator_compose.domain.calculation.priority

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import javax.inject.Inject

interface LowestPriorityAction {

    fun lowestPriorityAction(action: String, num: Double, num1: Double): Double

    class Base @Inject constructor(
        private val calc: PrimitiveCalculation.Base,
    ) : LowestPriorityAction {

        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS

        override fun lowestPriorityAction(
            action: String, num: Double, num1: Double
        ): Double {

            if (action.length != 1) throw IllegalArgumentException(Strings.EXCEPTION_ACTION_LENGTH_NOT_EQUAL_ONE)
            if (action != plus && action != minus) throw IllegalArgumentException(Strings.EXCEPTION_ACTION_NOT_EQUALS)

            when (action) {
                plus -> return calc.plus(num = num, num1 = num1)
                minus -> return calc.minus(num = num, num1 = num1)

            }

            return num
        }
    }

}