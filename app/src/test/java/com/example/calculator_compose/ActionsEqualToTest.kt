package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.calculation.ActionsEqualTo
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.model.DomainCalculationValues
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFailsWith

class ActionsEqualToTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val actionsEqualTo = ActionsEqualTo.Base(primitiveCalculation = primitiveCalculation)

    private val delta = 0.01

    @Test
    fun `calculation multiply with two positive numbers`() {
        val action = mutableListOf("*")
        val numbers = mutableListOf(5.0, 5.0)
        val text = "*"

        val index = action.indexOf(text)
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = actionsEqualTo.actionWithTwoNumbers(value = value, text = text)

        assertEquals(25.0, result.numbers[index], delta)
        assertTrue(action.isEmpty())
    }

    @Test
    fun `calculation with two action with two positive numbers`() {
        val action = mutableListOf("*", "-")
        val numbers = mutableListOf(5.0, 5.0)
        val text = "*"

        val index = action.indexOf(element = text)
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = actionsEqualTo.actionWithTwoNumbers(value = value, text = text)

        assertEquals(25.0, result.numbers[index], delta)
    }

    @Test
    fun `calculation sin with one positive numbers`() {
        val action = mutableListOf("sin")
        val numbers = mutableListOf(90.0)
        val text = "sin"

        val index = action.indexOf(text)
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = actionsEqualTo.actionWithOneNumbers(value = value, text = text)

        assertEquals(0.893996684, result.numbers[index], delta)
        assertTrue(action.isEmpty())
    }

    @Test
    fun `calculation sin and other action with one positive numbers`() {
        val action = mutableListOf("sin", "(", ")")
        val numbers = mutableListOf(90.0)
        val text = "sin"

        val index = action.indexOf(text)
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = actionsEqualTo.actionWithOneNumbers(value = value, text = text)

        assertEquals(0.893996684, result.numbers[index], delta)
    }

    @Test
    fun `calculation multiply action with one positive numbers`() {
        val action = mutableListOf("*")
        val numbers = mutableListOf(45.0)
        val text = "*"

        val value = DomainCalculationValues(numbers = numbers, action = action)

        val exception = assertFailsWith<IllegalArgumentException> {
            actionsEqualTo.actionWithTwoNumbers(value = value, text = text)
        }

        assertEquals("numbers count == 1 in action with two numbers", exception.message)
    }
}