package com.example.calculator_compose.domain

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface ConstCalculationRow {

    fun getCalculation(): PresentationValues

    fun setCalculation(value: PresentationValues)

    class Base @Inject constructor() : ConstCalculationRow {
        
        override fun getCalculation() = values

        override fun setCalculation(value: PresentationValues) {
            values = value
        }

        private companion object {
            var values: PresentationValues =
                PresentationValues(calculation = Strings.START_EXAMPLE, action = Strings.EMPTY)
        }
    }
}