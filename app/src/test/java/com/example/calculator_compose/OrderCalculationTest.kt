package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.calculation.ActionsEqualTo
import com.example.calculator_compose.domain.calculation.calculation.Calculation
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.priority.LowestPriorityAction
import com.example.calculator_compose.domain.calculation.priority.OrderCalculation
import com.example.calculator_compose.domain.model.DomainCalculationValues
import org.junit.Test
import kotlin.test.assertEquals

class OrderCalculationTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val lowestPriorityAction =
        LowestPriorityAction.Base(primitiveCalculation = primitiveCalculation)

    private val calculation =
        Calculation.Base(actionsEqualTo = ActionsEqualTo.Base(primitiveCalculation))

    private val orderCalculation = OrderCalculation.Base(
        lowestPriority = lowestPriorityAction, calculation = calculation
    )

    private val delta = 0.01

    @Test
    fun `order calculation example with 1 low priority action`() {

        val numbers = mutableListOf(3.0, 2.0)
        val action = mutableListOf("+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(5.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 1 priority action`() {

        val numbers = mutableListOf(3.0, 2.0)
        val action = mutableListOf("*")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(6.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 priority action`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0)
        val action = mutableListOf("*", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(1.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 different priority actions`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0)
        val action = mutableListOf("*", "+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(12.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 3 different priority actions`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0, 6.0)
        val action = mutableListOf("*", "+", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(7.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 4 different priority actions`() {

        val numbers = mutableListOf(5.0, 2.0, 6.0, 6.0)
        val action = mutableListOf("!", "*", "+", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(241.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 1 actions and brackets`() {

        val numbers = mutableListOf(2.0, 3.0)
        val action = mutableListOf("(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(5.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 actions and brackets`() {

        val numbers = mutableListOf(2.0, 3.0, 1.0)
        val action = mutableListOf("(", "+", "-", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(4.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 actions with other priority and brackets`() {

        val numbers = mutableListOf(2.0, 3.0, 2.0)
        val action = mutableListOf("(", "+", "*", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(8.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and cos`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("cos", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(-0.44807, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and tan`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("tan", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(-1.9952, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and sin`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("sin", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value)

        assertEquals(0.89399, result.numbers[0], delta)
    }

    @Test
    fun `high priority action example with one action and sin`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("sin", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.highPriorityAction(value)

        assertEquals(90.0, result.numbers[0], delta)
        assertEquals("sin", action[0])
        assertEquals(1, action.count())
    }

    @Test
    fun `priority action example with 1 actions`() {

        val numbers = mutableListOf(5.0)
        val action = mutableListOf("!")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.priorityAction(value)

        assertEquals(120.0, result.numbers[0], delta)
    }
}