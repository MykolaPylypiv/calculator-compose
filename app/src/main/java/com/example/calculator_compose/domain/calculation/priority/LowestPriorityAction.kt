package com.example.calculator_compose.domain.calculation.priority

import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import javax.inject.Inject

interface LowestPriorityAction {

    fun lowestPriorityAction(action: String, num: Double, num1: Double): Double

    class Base @Inject constructor(
        private val primitiveCalculation: PrimitiveCalculation.Base,
    ) : LowestPriorityAction {

        override fun lowestPriorityAction(
            action: String, num: Double, num1: Double
        ): Double {

            if (action.length != 1) {
                throw IllegalArgumentException("Action length not equal 1")
            }

            when (action) {
                "+" -> return primitiveCalculation.plus(num, num1)
                "-" -> return primitiveCalculation.minus(num, num1)
            }

            return num
        }
    }

}