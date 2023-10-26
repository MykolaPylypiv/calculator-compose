package com.example.calculator_compose

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.calculation.ExampleComponent
import com.example.calculator_compose.domain.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.Priority
import com.example.calculator_compose.domain.calculation.result.Result.Base
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.calculation.result.Calculation
import com.example.calculator_compose.domain.calculation.result.SelectSection
import com.example.calculator_compose.domain.calculation.result.UtilResult
import org.junit.Test
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.sin
import kotlin.test.assertEquals


class ResultTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val component = ExampleComponent.Base()

    private val mapper = MapperToDomainValues(component)

    private val priority =
        Priority.Base(primitiveCalculation = primitiveCalculation, component = component)

    private val section = SelectSection.Base()

    private val calculation = Calculation.Base(
        mapper = mapper, component = component, priority = priority, section = section
    )

    private val util = UtilResult.Base(component = component)

    private val result =
        Base(mapper = mapper, component = component, calculation = calculation, util = util)

    @Test
    fun zero() {
        val example = "0"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("0", result)
    }

    @Test
    fun `one number and one action`() {
        val example = "3+"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("3", result)
    }

    @Test
    fun `degrees and number`() {
        val example = "(3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("3", result)
    }

    @Test
    fun `numbers is empty`() {
        val example = ""
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("0", result)
    }

    @Test
    fun `end example equal action`() {
        val example = "sin(30)+3+"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("3.5", result)
    }

    @Test
    fun `end example equal trigonometric`() {
        val example = "3+3+sin("
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("6", result)
    }

    @Test
    fun `equal 1 action`() {
        val example = "3+3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("6", result)
    }

    @Test
    fun `equal 2 action`() {
        val example = "3+3-3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("3", result)
    }

    @Test
    fun `equal 2 action with other priority`() {
        val example = "3+3×3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("12", result)
    }

    @Test
    fun `equal 1 action with brackets`() {
        val example = "(3+3)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("6", result)
    }

    @Test
    fun `equal 2 action with brackets`() {
        val example = "(3+3-2)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("4", result)
    }

    @Test
    fun `equal 2 action with other priority brackets`() {
        val example = "(3+3×2)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("9", result)
    }

    @Test
    fun `equal 2 action with brackets and factorial`() {
        val example = "(3+3-2)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("4", result)
    }

    @Test
    fun `result 1 brackets and 1 action`() {
        val example = "6+(4-3)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("7", result)
    }

    @Test
    fun `two trigonometric action`() {
        val example = "sin(30)+sin(30)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("1", result)
    }

    @Test
    fun `result 1 brackets and 1 trigonometric`() {
        val example = "sin(30)+(4-3)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("1.5", result)
    }

    @Test
    fun `result 1 brackets and 1 trigonometric `() {
        val example = "(4-3)+sin(30)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("1.5", result)
    }

    @Test
    fun `result 2 brackets`() {
        val example = "(3+3)+(4-3)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("7", result)
    }

    @Test
    fun `equal sin`() {
        val example = "sin(90)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(sin(90.0 * Math.PI / 180).toInt().toString(), result)
    }

    @Test
    fun `equal sin with 2 action`() {
        val example = "sin(45+45)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("1", result)
    }

    @Test
    fun `equal cos`() {
        val example = "cos(90)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("0", result)
    }

    @Test
    fun `equal cos with 2 action`() {
        val example = "cos(45+45)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("0", result)
    }

    @Test
    fun `equal lg`() {
        val example = "lg(90)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(log10(90.0).toString(), result)
    }

    @Test
    fun `equal lg with 2 action`() {
        val example = "lg(45+45)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(log10(90.0).toString(), result)
    }

    @Test
    fun `equal ln`() {
        val example = "ln(90)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(ln(90.0).toString(), result)
    }

    @Test
    fun `equal tan`() {
        val example = "tan(90)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("2.147483647", result)
    }

    @Test
    fun `equal tan with 2 action`() {
        val example = "tan(45+45)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("2.147483647", result)
    }

    @Test
    fun `equal zero factorial`() {
        val example = "0!"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("1", result)
    }

    @Test
    fun `equal zero factorial with one action`() {
        val example = "0!+1"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("2", result)
    }

    @Test
    fun `equal factorial with one action`() {
        val example = "5!+1"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("121", result)
    }

    @Test
    fun `equal square root`() {
        val example = "√25"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("5", result)
    }

    @Test
    fun `equal square root with one action`() {
        val example = "√25+1"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("6", result)
    }

    @Test
    fun `equal one minus number`() {
        val example = "0-5"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("-5", result)
    }

    @Test
    fun `equal minus number with one action`() {
        val example = "0-5+1"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("-4", result)
    }

    @Test
    fun `equal minus pow with one action`() {
        val example = "5^(0-1)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("0.2", result)
    }

    @Test
    fun `equal arcsin`() {
        val example = "asin(0.5)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("30", result)
    }

    @Test
    fun `equal arcsin with 1 action`() {
        val example = "asin(0.3 + 0.2)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("30", result)
    }

    @Test
    fun `equal zero division exception`() {
        val example = "2÷0"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(Double.POSITIVE_INFINITY, result.toDouble())
    }

    @Test
    fun `pi + number`() {
        val example = "π+3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(6.14159265, result.toDouble())
    }

    @Test
    fun `e + number`() {
        val example = "e+3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(5.7182818300000005, result.toDouble())
    }

    @Test
    fun `sin(30)`() {
        val example = "sin(30)"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(0.5, result.toDouble())
    }

    @Test
    fun `sin + number`() {
        val example = "sin(30)+3"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals(3.5, result.toDouble())
    }

    @Test
    fun `float + float`() {
        val example = "2.3 + 0.2"
        val isRadians = Strings.DEGREES

        val result = result.renewal(example, isRadians)

        assertEquals("2.5", result)
    }
}