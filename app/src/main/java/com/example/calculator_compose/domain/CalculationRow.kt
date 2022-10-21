package com.example.calculator_compose.domain

import com.example.calculator_compose.domain.model.Values
import javax.inject.Inject

interface CalculationRow {

    fun getCalculation(): Values

    fun setCalculation(value: Values)

    class Base @Inject constructor() : CalculationRow {

        private companion object {
            var values: Values = Values(calculation = "null", action = "")
        }

        override fun getCalculation() = values

        override fun setCalculation(value: Values) {
            values = value
        }
    }
}