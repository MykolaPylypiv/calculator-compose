package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.calculation.ActionsEqualTo
import com.example.calculator_compose.domain.calculation.calculation.Calculation
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.priority.LowestPriorityAction
import com.example.calculator_compose.domain.calculation.priority.OrderCalculation
import com.example.calculator_compose.domain.model.DomainCalculationValues
import org.junit.Test
import kotlin.math.cos
import kotlin.test.assertEquals

class OrderCalculationTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val lowestPriorityAction =
        LowestPriorityAction.Base(calc = primitiveCalculation)

    private val calculation =
        Calculation.Base(actionsEqualTo = ActionsEqualTo.Base(primitiveCalculation))

    private val orderCalculation = OrderCalculation.Base(
        lowestPriority = lowestPriorityAction, calculation = calculation
    )

    private val delta = 0.01
    private val isRadians = false

    @Test
    fun `order calculation example with 1 low priority action`() {

        val numbers = mutableListOf(3.0, 2.0)
        val action = mutableListOf("+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(5.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 1 priority action`() {

        val numbers = mutableListOf(3.0, 2.0)
        val action = mutableListOf("*")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(6.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 priority action`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0)
        val action = mutableListOf("*", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(1.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 different priority actions`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0)
        val action = mutableListOf("*", "+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(12.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 3 different priority actions`() {

        val numbers = mutableListOf(3.0, 2.0, 6.0, 6.0)
        val action = mutableListOf("*", "+", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(7.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 4 different priority actions`() {

        val numbers = mutableListOf(5.0, 2.0, 6.0, 6.0)
        val action = mutableListOf("!", "*", "+", "/")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(241.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 1 actions and brackets`() {

        val numbers = mutableListOf(2.0, 3.0)
        val action = mutableListOf("(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(5.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 actions and brackets`() {

        val numbers = mutableListOf(2.0, 3.0, 1.0)
        val action = mutableListOf("(", "+", "-", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(4.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with 2 actions with other priority and brackets`() {

        val numbers = mutableListOf(2.0, 3.0, 2.0)
        val action = mutableListOf("(", "+", "*", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(8.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and cos`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("cos", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(0.0, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and tan`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("tan", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(2.147483647, result.numbers[0], delta)
    }

    @Test
    fun `order calculation example with one action and sin`() {

        val numbers = mutableListOf(45.0, 45.0)
        val action = mutableListOf("sin", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(1.0, result.numbers[0], delta)
    }

    @Test
    fun `sin + number`() {

        val numbers = mutableListOf(30.0, 3.0)
        val action = mutableListOf("sin", "(", ")", "+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(3.5, result.numbers[0], delta)
    }

    @Test
    fun `cos + number`() {

        val numbers = mutableListOf(60.0, 3.0)
        val action = mutableListOf("cos", "(", ")", "+")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(3.5, result.numbers[0], delta)
    }

    @Test
    fun `number + sin`() {

        val numbers = mutableListOf(3.0, 30.0)
        val action = mutableListOf("+", "sin", "(", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(3.5, result.numbers[0], delta)
    }

    @Test
    fun `number + cos`() {

        val numbers = mutableListOf(3.0, 60.0)
        val action = mutableListOf("+", "cos", "(", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(3.5, result.numbers[0], delta)
    }

    @Test
    fun `two brackets`() {

        val numbers = mutableListOf(5.0, 3.0, 5.0, 4.0)
        val action = mutableListOf("(", "+", ")", "+", "(", "+", ")")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.orderCalculation(value, isRadians)

        assertEquals(17.0, result.numbers[0], delta)
    }

    @Test
    fun `priority action example with 1 actions`() {

        val numbers = mutableListOf(5.0)
        val action = mutableListOf("!")
        val value = DomainCalculationValues(numbers = numbers, action = action)

        val result = orderCalculation.priorityAction(value, isRadians)

        assertEquals(120.0, result.numbers[0], delta)
    }
}