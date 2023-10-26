package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.result.SelectSection
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class SelectSectionTest {

    private val result = SelectSection.Base()

    @Test
    fun actionPlus() {
        val example = "sin(30)+sin(60)"

        val result = result.selectSection(example)

        Assert.assertEquals("30", result)
    }

    @Test
    fun `in trigonometric action`() {
        val example = "sin(30+30)"

        val result = result.selectSection(example)

        Assert.assertEquals("30+30", result)
    }

    @Test
    fun `simple action`() {
        val example = "(3+6-9)+(3-6)"

        val result = result.selectSection(example)

        Assert.assertEquals("3+6-9", result)
    }

    @Test
    fun `simple action without brackets one action`() {
        val example = "(3+3)"

        val result = result.selectSection(example)

        Assert.assertEquals("3+3", result)
    }

    @Test
    fun `simple action without brackets`() {
        val example = "3+6-9"

        val result = result.selectSection(example)

        Assert.assertEquals("3+6-9", result)
    }

    @Test
    fun `minus number with one action`() {
        val example = "-5+1"

        val result = result.selectSection(example)

        assertEquals("-5+1", result)
    }
}