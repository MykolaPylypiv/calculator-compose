package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.priority.LowestPriorityAction
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class LowestPriorityActionTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val calculation = LowestPriorityAction.Base(primitiveCalculation = primitiveCalculation)

    private val delta = 0.00

    @Test
    fun `calculation plus with positive numbers`() {
        val expected = 7.0
        val num = 3.0
        val num1 = 4.0
        val action = "+"

        val result = calculation.lowestPriorityAction(num = num, num1 = num1, action = action)

        assertEquals(expected, result, delta)
    }

    @Test
    fun `calculation minus with positive numbers`() {
        val expected = 3.0
        val num = 4.0
        val num1 = 1.0
        val action = "-"

        val result = calculation.lowestPriorityAction(num = num, num1 = num1, action = action)

        assertEquals(expected, result, delta)
    }

    @Test
    fun `calculation minus with one negative numbers`() {
        val expected = -3.0
        val num = 4.0
        val num1 = 7.0
        val action = "-"

        val result = calculation.lowestPriorityAction(num = num, num1 = num1, action = action)

        assertEquals(expected, result, delta)
    }

    @Test
    fun `calculation minus with negative numbers`() {
        val expected = 3.0
        val num = -4.0
        val num1 = -7.0
        val action = "-"

        val result = calculation.lowestPriorityAction(num = num, num1 = num1, action = action)

        assertEquals(expected, result, delta)
    }

    @Test
    fun `calculation multiply exception`() {
        val num = 4.0
        val num1 = 7.0
        val action = "*"

        val exception = assertFailsWith<IllegalArgumentException> {
            calculation.lowestPriorityAction(num = num, num1 = num1, action = action)
        }

        assertEquals("Action not equal + or -", exception.message)
    }

    @Test
    fun `action not equal one exception`() {
        val num = 10.0
        val num1 = 10.0
        val action = "*-"

        val exception = assertFailsWith<IllegalArgumentException> {
            calculation.lowestPriorityAction(num = num, num1 = num1, action = action)
        }

        assertEquals("Action length not equal 1", exception.message)
    }

}