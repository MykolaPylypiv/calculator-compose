package com.example.calculator_compose.domain.calculation.mapper

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.core.Mapper
import com.example.calculator_compose.domain.model.DomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

class MapperToDomainValues @Inject constructor() : Mapper<PresentationValues, DomainValues> {

    private val asin = Strings.ACTION_ARCSIN.dropLast(1)
    private val acos = Strings.ACTION_ARCCOS.dropLast(1)
    private val atan = Strings.ACTION_ARCTAN.dropLast(1)
    private val sin = Strings.ACTION_SIN.dropLast(1)
    private val cos = Strings.ACTION_COS.dropLast(1)
    private val tan = Strings.ACTION_TAN.dropLast(1)
    private val lg = Strings.ACTION_LG.dropLast(1)
    private val ln = Strings.ACTION_LN.dropLast(1)
    private val plus = Strings.ACTION_PLUS
    private val minus = Strings.ACTION_MINUS
    private val multiply = Strings.ACTION_MULTIPLY
    private val division = Strings.ACTION_DIVISION
    private val percent = Strings.ACTION_PERCENT
    private val pow = Strings.ACTION_POW
    private val squareRoot = Strings.ACTION_SQUARE_ROOT
    private val factorial = Strings.ACTION_FACTORIAL
    private val leftBr = Strings.LEFT_BRACKET
    private val rightBr = Strings.RIGHT_BRACKET

    override fun map(data: PresentationValues): DomainValues {

        val action: MutableList<String> = mutableListOf()

        val newAction = data.action.replace(oldValue = asin, newValue = "i")
            .replace(oldValue = acos, newValue = "o").replace(oldValue = atan, newValue = "x")
            .replace(oldValue = sin, newValue = "s").replace(oldValue = cos, newValue = "c")
            .replace(oldValue = tan, newValue = "t").replace(oldValue = lg, newValue = "l")
            .replace(oldValue = ln, newValue = "n")

        val numbers = data.calculation.split(
            plus,
            minus,
            multiply,
            division,
            percent,
            pow,
            squareRoot,
            factorial,
            leftBr,
            rightBr,
            asin,
            acos,
            atan,
            sin,
            cos,
            tan,
            lg,
            ln
        ).toMutableList()

        val removeNumber = mutableListOf(Strings.EMPTY)

        for (_action in newAction) {

            when (val value = _action.toString()) {
                "i" -> action.add(element = asin)

                "o" -> action.add(element = acos)
                "x" -> action.add(element = atan)

                "s" -> action.add(element = sin)

                "c" -> action.add(element = cos)

                "t" -> action.add(element = tan)

                "l" -> action.add(element = lg)

                "n" -> action.add(element = ln)

                else -> action.add(element = value)
            }
        }

        numbers.removeAll(elements = removeNumber)

        return DomainValues(numbers = numbers, action = action)
    }
}