package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.*

class PrimitiveCalculationTest {

    private val num = 3.0

    private val num1 = 2.0

    private val delta = 0.00

    private val action = PrimitiveCalculation.Base()

    @Test
    fun actionPlus() {
        val result = action.plus(num, num1)

        assertEquals(num + num1, result, delta)
    }

    @Test
    fun actionMinus() {
        val result = action.minus(num, num1)

        assertEquals(num - num1, result, delta)
    }

    @Test
    fun actionMultiply() {
        val result = action.multiply(num, num1)

        assertEquals(num * num1, result, delta)
    }

    @Test
    fun actionDivision() {
        val result = action.division(num, num1)

        assertEquals(num / num1, result, delta)
    }

    @Test
    fun actionPercent() {
        val result = action.percent(num, num1)

        assertEquals(num % num1, result, delta)
    }

    @Test
    fun actionPower() {
        val result = action.power(num, num1)

        assertEquals(num.pow(num1), result, delta)
    }

    @Test
    fun actionSqrt() {
        val result = action.sqrt(num)

        assertEquals(sqrt(num), result, delta)
    }

    @Test
    fun actionSin() {
        val result = action.sin(num)

        assertEquals(sin(num), result, delta)
    }

    @Test
    fun actionCos() {
        val result = action.cos(num)

        assertEquals(cos(num), result, delta)
    }

    @Test
    fun actionTan() {
        val result = action.tan(num)

        assertEquals(tan(num), result, delta)
    }

    @Test
    fun actionLg() {
        val result = action.lg(num)

        assertEquals(log10(num), result, delta)
    }

    @Test
    fun actionFactorial() {
        val result = action.factorial(num.toInt())

        assertEquals((1..num.toInt()).reduce(Int::times), result)
    }

    @Test
    fun letterNumberPi() {
        val result = action.numPi()

        assertEquals(Math.PI, result, delta)
    }

    @Test
    fun letterNumberE() {
        val result = action.numE()

        assertEquals(Math.E, result, delta)
    }
}