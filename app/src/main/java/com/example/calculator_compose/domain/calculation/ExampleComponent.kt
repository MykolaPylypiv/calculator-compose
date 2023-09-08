package com.example.calculator_compose.domain.calculation

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface ExampleComponent {
    val numbers: MutableList<String>

    val action: MutableList<String>

    val actionWithOneNumber: MutableList<String>

    val simple: MutableList<String>

    val second: MutableList<String>

    val actionWithThoNumber: MutableList<String>

    val personal: MutableList<String>

    val trigonometric: MutableList<String>

    val logarithms: MutableList<String>

    val brackets: MutableList<String>

    class Base @Inject constructor() : ExampleComponent {
        val zero = Strings.NUMBER_ZERO
        private val one = Strings.NUMBER_ONE
        private val two = Strings.NUMBER_TWO
        private val three = Strings.NUMBER_THREE
        private val four = Strings.NUMBER_FOUR
        private val five = Strings.NUMBER_FIVE
        private val six = Strings.NUMBER_SIX
        private val seven = Strings.NUMBER_SEVEN
        private val eight = Strings.NUMBER_EIGHT
        private val nine = Strings.NUMBER_NINE

        val minus = Strings.ACTION_MINUS
        val plus = Strings.ACTION_PLUS
        val multiply = Strings.ACTION_MULTIPLY
        val division = Strings.ACTION_DIVISION
        val percent = Strings.ACTION_PERCENT
        val pow = Strings.ACTION_POW

        val factorial = Strings.ACTION_FACTORIAL
        val sqrt = Strings.ACTION_SQUARE_ROOT

        val sin = Strings.ACTION_SIN.dropLast(1)
        val cos = Strings.ACTION_COS.dropLast(1)
        val tan = Strings.ACTION_TAN.dropLast(1)
        val lg = Strings.ACTION_LG.dropLast(1)
        val ln = Strings.ACTION_LN.dropLast(1)

        val asin = Strings.ACTION_ARCSIN.dropLast(1)
        val acos = Strings.ACTION_ARCCOS.dropLast(1)
        val atan = Strings.ACTION_ARCTAN.dropLast(1)

        private val leftBr = Strings.LEFT_BRACKET
        private val rightBr = Strings.RIGHT_BRACKET

        val pi = Strings.NUMBER_P
        val e = Strings.NUMBER_E

        val deg = Strings.DEGREES

        override val simple = mutableListOf(plus, minus)

        override val second = mutableListOf(multiply, division, percent, pow)

        override val actionWithThoNumber: MutableList<String> = (simple + second).toMutableList()

        override val personal = mutableListOf(sqrt, factorial)

        override val trigonometric = mutableListOf(sin, cos, tan, acos, asin, atan)

        override val logarithms = mutableListOf(lg, ln)

        override val brackets = mutableListOf(leftBr, rightBr)

        override val numbers =
            mutableListOf(zero, one, two, three, four, five, six, seven, eight, nine)

        override val action =
            (simple + second + personal + trigonometric + logarithms + brackets).toMutableList()

        override val actionWithOneNumber = (personal + trigonometric + logarithms).toMutableList()
    }
}