package com.example.calculator_compose.domain.calculation.additional

import com.example.calculator_compose.domain.model.DomainAllValues
import javax.inject.Inject

interface EqualReturn {

    fun equalReturn(result: Double, example: String): DomainAllValues

    class Base @Inject constructor() : EqualReturn {

        override fun equalReturn(result: Double, example: String): DomainAllValues {
            val history = "\n\n$example\n = $result"
            val stringResult = result.toString()

            return if (stringResult.length >= 2 && stringResult.drop(stringResult.length - 2) == ".0") {
                DomainAllValues(
                    calculation = stringResult.dropLast(2), action = "", history = history
                )
            } else DomainAllValues(
                calculation = stringResult, action = "", history = history
            )
        }

    }
}