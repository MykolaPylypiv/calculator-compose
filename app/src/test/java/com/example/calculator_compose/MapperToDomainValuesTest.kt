package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import org.junit.Test
import kotlin.test.assertEquals

class MapperToDomainValuesTest {

    private val component = ExampleComponent.Base()

    private val mapper = MapperToDomainValues(component)

    @Test
    fun `mapper action`() {
        val example = "3+3"

        val result = mapper.map(example)

        assertEquals(mutableListOf("+"), result.action)
        assertEquals(mutableListOf("3", "3"), result.numbers)
    }

    @Test
    fun `mapper action sin`() {
        val example = "sin(90)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("sin", "(", ")"), result.action)
        assertEquals(mutableListOf("90"), result.numbers)
    }

    @Test
    fun `mapper action with 1 action and sin`() {
        val example = "sin(45+45)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("sin", "(", "+", ")"), result.action)
        assertEquals(mutableListOf("45", "45"), result.numbers)
    }

    @Test
    fun `two trigonometric action`() {
        val example = "sin(30)+sin(30)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("sin", "(", ")", "+", "sin", "(", ")"), result.action)
        assertEquals(mutableListOf("30", "30"), result.numbers)
    }

    @Test
    fun `mapper action with 1 action and cos`() {
        val example = "cos(45+45)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("cos", "(", "+", ")"), result.action)
        assertEquals(mutableListOf("45", "45"), result.numbers)
    }

    @Test
    fun `mapper asin`() {
        val example = "asin(0.5)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("asin", "(", ")"), result.action)
        assertEquals(mutableListOf("0.5"), result.numbers)
    }

    @Test
    fun `equal arcsin with 1 action`() {
        val example = "asin(0.3 + 0.2)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("asin", "(", "+", ")"), result.action)
        assertEquals(mutableListOf("0.3", "0.2"), result.numbers)
    }

    @Test
    fun `mapper acos`() {
        val example = "acos(0.5)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("acos", "(", ")"), result.action)
        assertEquals(mutableListOf("0.5"), result.numbers)
    }

    @Test
    fun `mapper atan`() {
        val example = "atan(0.5)"

        val result = mapper.map(example)

        assertEquals(mutableListOf("atan", "(", ")"), result.action)
        assertEquals(mutableListOf("0.5"), result.numbers)
    }

    @Test
    fun `pi + number`() {
        val example = "π+3"

        val result = mapper.map(example)

        assertEquals(mutableListOf("+"), result.action)
        assertEquals(mutableListOf("3.14159265", "3"), result.numbers)
    }
}