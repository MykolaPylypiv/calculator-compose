package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.model.PresentationValues
import org.junit.Test
import kotlin.test.assertEquals

class MapperToDomainValuesTest {

    private val mapper = MapperToDomainValues()

        @Test
    fun `mapper action`() {
        val example = "3+3"
        val action = "+"

        val values = PresentationValues(calculation = example, action = action)
        val result = mapper.map(values)

        assertEquals(mutableListOf("+"), result.action)
        assertEquals(mutableListOf("3", "3"), result.numbers)
    }

    @Test
    fun `mapper action sin`() {
        val example = "sin(90)"
        val action = "sin()"

        val values = PresentationValues(calculation = example, action = action)
        val result = mapper.map(values)

        assertEquals(mutableListOf("sin", "(", ")"), result.action)
        assertEquals(mutableListOf("90"), result.numbers)
    }

    @Test
    fun `mapper action with 1 action and sin`() {
        val example = "sin(45+45)"
        val action = "sin(+)"

        val values = PresentationValues(calculation = example, action = action)
        val result = mapper.map(values)

        assertEquals(mutableListOf("sin", "(", "+", ")"), result.action)
        assertEquals(mutableListOf("45", "45"), result.numbers)
    }

    @Test
    fun `mapper action with 1 action and cos`() {
        val example = "cos(45+45)"
        val action = "cos(+)"

        val values = PresentationValues(calculation = example, action = action)
        val result = mapper.map(values)

        assertEquals(mutableListOf("cos", "(", "+", ")"), result.action)
        assertEquals(mutableListOf("45", "45"), result.numbers)
    }
}