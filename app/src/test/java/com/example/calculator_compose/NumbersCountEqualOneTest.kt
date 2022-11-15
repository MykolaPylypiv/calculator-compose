package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.additional.EqualReturn
import com.example.calculator_compose.domain.calculation.additional.NumbersCountEqualOne
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import org.junit.Test
import kotlin.math.sin
import kotlin.test.assertEquals

class NumbersCountEqualOneTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val additional = EqualReturn.Base()

    private val check =
        NumbersCountEqualOne.Base(calc = primitiveCalculation, additional = additional)

    @Test
    fun `equal 1 action deg`() {
        val action = mutableListOf("sin", "(", ")")
        val numbers = mutableListOf("90")
        val example = "sin(90)"

        val result = check.check(action = action, numbers = numbers, example = example, isRadians = "deg")

        assertEquals(sin(90.0*Math.PI/180).toInt().toString(), result.calculation)
    }

    @Test
    fun `equal 1 action rad`() {
        val action = mutableListOf("sin", "(", ")")
        val numbers = mutableListOf("90")
        val example = "sin(90)"

        val result = check.check(action = action, numbers = numbers, example = example, isRadians = "rad")

        assertEquals(sin(90.0).toString(), result.calculation)
    }
}