package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.calculation.EqualReturn
import com.example.calculator_compose.domain.calculation.result.Result
import com.example.calculator_compose.domain.model.DomainAllValues
import javax.inject.Inject

interface EqualUseCase {

    fun equal(
        example: String, operation: String, history: String, isRadians: String = "deg"
    ): DomainAllValues

    class Base @Inject constructor(
        private val additional: EqualReturn.Base,
        private val result: Result.Base
    ) : EqualUseCase {

        override fun equal(
            example: String, operation: String, history: String, radians: String
        ): DomainAllValues {
            val result = result.renewal(example = example, radians = radians).toDouble()

            return additional.equalReturn(result = result, example = example)
        }
    }
}


