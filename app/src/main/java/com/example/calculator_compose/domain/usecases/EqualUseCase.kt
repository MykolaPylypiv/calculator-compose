package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.additional.EqualCheck
import com.example.calculator_compose.domain.calculation.additional.EqualReturn
import com.example.calculator_compose.domain.calculation.additional.NumbersCountEqualOne
import com.example.calculator_compose.domain.calculation.additional.Result
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainCalculationValues
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.calculation.priority.OrderCalculation
import com.example.calculator_compose.domain.model.DomainAllValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface EqualUseCase {

    fun equal(
        example: String, operation: String, history: String, isRadians: String = "deg"
    ): DomainAllValues

    class Base @Inject constructor(
        private val additional: EqualReturn.Base,
        private val orderCalculation: OrderCalculation.Base,
        private val check: EqualCheck.Base,
        private val checkCountOne: NumbersCountEqualOne.Base,
        private val mapper: MapperToDomainValues,
        private val mapperToCalculation: MapperToDomainCalculationValues,
        private val result: Result.Base
    ) : EqualUseCase {

        private val zero = Strings.NUMBER_ZERO
        private val rightBr = Strings.RIGHT_BRACKET
        private val startExample = Strings.START_EXAMPLE
        private val plus = Strings.ACTION_PLUS
        private val minus = Strings.ACTION_MINUS
        private val factorial = Strings.ACTION_FACTORIAL
        private val pi = Strings.NUMBER_P
        private val e = Strings.NUMBER_E
        private val deg = Strings.DEGREES

        override fun equal(
            example: String, operation: String, history: String, radians: String
        ): DomainAllValues {
            val isRadians = radians != deg
            if (example != zero) {
                val finalExample = example.replace("π", "3.14159265").replace("e", "2.71828183")

                val data = PresentationValues(
                    calculation = finalExample, action = operation
                )

                var values = mapper.map(data = data)
                values = check.initialCheck(values = values, example = example)

                val numbers = values.numbers
                val action = values.action

                if (example == pi || example == e || example == zero + factorial) return check.firstCheck(
                    example = example
                )

                if (operation.isNotEmpty() && example.last().toString() != operation.split(
                        factorial, rightBr
                    ).last() && example != startExample
                ) {
                    if (numbers.count() == 1) return checkCountOne.check(
                        action = action, example = example, numbers = numbers, isRadians = radians
                    )

                    if (numbers.count() > 1) {
                        val value = mapperToCalculation.map(data = values)
                        val result = orderCalculation.orderCalculation(value = value, isRadians = isRadians)

                        return additional.equalReturn(result = result.numbers[0], example = example)
                    }
                }

            }
            return DomainAllValues(calculation = example, action = operation, history = history)
        }
    }
}


