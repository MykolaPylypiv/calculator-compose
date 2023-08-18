package com.example.calculator_compose.domain.calculation

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.DomainAllValues
import javax.inject.Inject

interface EqualReturn {

    fun equalReturn(result: Double, example: String): DomainAllValues

    class Base @Inject constructor() : EqualReturn {

        private val empty = Strings.EMPTY
        private val point = Strings.POINT
        private val zero = Strings.NUMBER_ZERO

        override fun equalReturn(result: Double, example: String): DomainAllValues {
            val stringResult = result.toString()

            return if (stringResult.length >= 2 && stringResult.drop(n = stringResult.length - 2) == point + zero) {
                val history = "\n\n$example\n = ${stringResult.dropLast(2)}"
                DomainAllValues(
                    calculation = stringResult.dropLast(2), action = empty, history = history
                )
            } else {
                val history = "\n\n$example\n = $stringResult"

                DomainAllValues(
                    calculation = stringResult, action = empty, history = history
                )
            }
        }

    }
}