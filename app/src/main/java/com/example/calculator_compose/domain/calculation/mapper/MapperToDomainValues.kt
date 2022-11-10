package com.example.calculator_compose.domain.calculation.mapper

import com.example.calculator_compose.core.Mapper
import com.example.calculator_compose.domain.model.DomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

class MapperToDomainValues @Inject constructor() : Mapper<PresentationValues, DomainValues> {

    override fun map(data: PresentationValues): DomainValues {
        val action: MutableList<String> = mutableListOf()

        val newAction = data.action
            .replace("sin", "s")
            .replace("cos", "c")
            .replace("tan", "t")
            .replace("lg", "l")
            .replace("ln", "n")

        val numbers = data.calculation.split(
            "+", "-", "*", "/", "%", "^", "√", "!", "(", ")", "sin", "cos", "tan", "lg", "ln"
        ).toMutableList()

        val removeAction = mutableListOf("i", "n", "o", "s", "a", "n", "l", "g", "")
        val removeNumber = mutableListOf("")

        for (_action in newAction) {
            val value = _action.toString()
            if (value != "s" && value != "c" && value != "t") {
                action.add(value)
            }

            if (value == "s") {
                action.add("sin")
            }

            if (value == "c") {
                action.add("cos")
            }

            if (value == "t") {
                action.add("tan")
            }

            if (value == "l") {
                action.add("lg")
            }

            if (value == "n") {
                action.add("ln")
            }
        }

        action.removeAll(removeAction)
        numbers.removeAll(removeNumber)

        return DomainValues(numbers = numbers, action = action)
    }
}