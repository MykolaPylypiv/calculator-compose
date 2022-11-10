package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.calculation.additional.EqualCheck
import com.example.calculator_compose.domain.calculation.additional.EqualReturn
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainCalculationValues
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.calculation.priority.OrderCalculation
import com.example.calculator_compose.domain.model.DomainAllValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface EqualUseCase {

    fun equal(example: String, operation: String, history: String): DomainAllValues

    class Base @Inject constructor(
        private val additional: EqualReturn.Base,
        private val orderCalculation: OrderCalculation.Base,
        private val check: EqualCheck.Base,
        private val mapper: MapperToDomainValues,
        private val mapperToCalculation: MapperToDomainCalculationValues
    ) : EqualUseCase {

        override fun equal(example: String, operation: String, history: String): DomainAllValues {
            var values = mapper.map(PresentationValues(calculation = example, action = operation))
            values = check.initialCheck(values = values, example = example)

            val numbers = values.numbers
            val action = values.action

            if (example == "π" || example == "e" || example == "0!") return check.firstCheck(
                example
            )

            if (operation.isNotEmpty() && example.last().toString() != operation.split("!", ")")
                    .last() && example != "null"
            ) {

                if (numbers.count() == 1) return check.secondCheck(action, example, numbers)

                if (numbers.count() > 1) {
                    val value = mapperToCalculation.map(values)
                    val result = orderCalculation.orderCalculation(value)

                    return additional.equalReturn(result = result.numbers[0], example = example)
                }
            }

            return DomainAllValues(calculation = example, action = operation, history = history)
        }
    }
}


