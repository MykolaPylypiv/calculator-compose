package com.example.calculator_compose.domain.calculation.mapper

import com.example.calculator_compose.core.Mapper
import com.example.calculator_compose.domain.model.DomainCalculationValues
import com.example.calculator_compose.domain.model.DomainValues
import javax.inject.Inject

class MapperToDomainCalculationValues @Inject constructor() :
    Mapper<DomainValues, DomainCalculationValues> {

    override fun map(data: DomainValues) = DomainCalculationValues(
        numbers = data.numbers.map {
             return@map it.toDouble()
        }.toMutableList(), action = data.action
    )
}