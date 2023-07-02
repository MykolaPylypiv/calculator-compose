package com.example.calculator_compose

import com.example.calculator_compose.domain.calculation.additional.EqualCheck
import com.example.calculator_compose.domain.calculation.additional.EqualReturn
import com.example.calculator_compose.domain.calculation.additional.NumbersCountEqualOne
import com.example.calculator_compose.domain.calculation.additional.Result
import com.example.calculator_compose.domain.calculation.calculation.ActionsEqualTo
import com.example.calculator_compose.domain.calculation.calculation.Calculation
import com.example.calculator_compose.domain.calculation.calculation.PrimitiveCalculation
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainCalculationValues
import com.example.calculator_compose.domain.calculation.mapper.MapperToDomainValues
import com.example.calculator_compose.domain.calculation.priority.LowestPriorityAction
import com.example.calculator_compose.domain.calculation.priority.OrderCalculation
import com.example.calculator_compose.domain.usecases.EqualUseCase
import org.junit.Test
import kotlin.math.*
import kotlin.test.assertEquals

class EqualUseCaseTest {

    private val primitiveCalculation = PrimitiveCalculation.Base()

    private val additional = EqualReturn.Base()

    private val actionsEqualTo = ActionsEqualTo.Base(primitiveCalculation = primitiveCalculation)

    private val lowestPriority = LowestPriorityAction.Base(calc = primitiveCalculation)

    private val calculation = Calculation.Base(actionsEqualTo = actionsEqualTo)

    private val orderCalculation =
        OrderCalculation.Base(calculation = calculation, lowestPriority = lowestPriority)

    private val check = EqualCheck.Base(additional = additional, calc = primitiveCalculation)

    private val mapper = MapperToDomainValues()

    private val mapperToCalculation = MapperToDomainCalculationValues()

    private val countEqualOne =
        NumbersCountEqualOne.Base(calc = primitiveCalculation, additional = additional)

    private val result = Result.Base(mapper = mapper, primitiveCalculation = primitiveCalculation)

    private val equal = EqualUseCase.Base(
        additional = additional,
        orderCalculation = orderCalculation,
        check = check,
        checkCountOne = countEqualOne,
        mapper = mapper,
        mapperToCalculation = mapperToCalculation,
        result = result
    )

    @Test
    fun zero() {
        val example = "0"
        val operation = ""
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("0", result.calculation)
    }

    @Test
    fun `equal 1 action`() {
        val example = "3+3"
        val operation = "+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("6", result.calculation)
    }

    @Test
    fun `equal 2 action`() {
        val example = "3+3-3"
        val operation = "+-"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("3", result.calculation)
    }

    @Test
    fun `equal 2 action with other priority`() {
        val example = "3+3*3"
        val operation = "+*"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("12", result.calculation)
    }

    @Test
    fun `equal 1 action with brackets`() {
        val example = "(3+3)"
        val operation = "(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("6", result.calculation)
    }

    @Test
    fun `equal 2 action with brackets`() {
        val example = "(3+3-2)"
        val operation = "(+-)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("4", result.calculation)
    }

    @Test
    fun `equal 2 action with other priority brackets`() {
        val example = "(3+3*2)"
        val operation = "(+*)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("9", result.calculation)
    }

    @Test
    fun `equal 2 action with brackets and factorial`() {
        val example = "(3+3-2)"
        val operation = "(+-)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("4", result.calculation)
    }

    @Test
    fun `equal sin`() {
        val example = "sin(90)"
        val operation = "sin(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(sin(90.0 * Math.PI / 180).toInt().toString(), result.calculation)
    }

    @Test
    fun `equal sin with 2 action`() {
        val example = "sin(45+45)"
        val operation = "sin(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("1", result.calculation)
    }

    @Test
    fun `equal cos`() {
        val example = "cos(90)"
        val operation = "cos()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("0", result.calculation)
    }

    @Test
    fun `equal cos with 2 action`() {
        val example = "cos(45+45)"
        val operation = "cos(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("-0.448073616", result.calculation)
    }

    @Test
    fun `equal lg`() {
        val example = "lg(90)"
        val operation = "lg()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(log10(90.0).toString(), result.calculation)
    }

    @Test
    fun `equal lg with 2 action`() {
        val example = "lg(45+45)"
        val operation = "lg(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(log10(90.0).toString(), result.calculation)
    }

    @Test
    fun `equal ln`() {
        val example = "ln(90)"
        val operation = "ln()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(ln(90.0).toString(), result.calculation)
    }

    @Test
    fun `equal tan`() {
        val example = "tan(90)"
        val operation = "tan()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("2.147483647", result.calculation)
    }

    @Test
    fun `equal tan with 2 action`() {
        val example = "tan(45+45)"
        val operation = "tan(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("2.147483647", result.calculation)
    }

    @Test
    fun `equal zero factorial`() {
        val example = "0!"
        val operation = "!"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("1", result.calculation)
    }

    @Test
    fun `equal zero factorial with one action`() {
        val example = "0!+1"
        val operation = "!+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("2", result.calculation)
    }

    @Test
    fun `equal square root`() {
        val example = "√25"
        val operation = "√"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("5", result.calculation)
    }

    @Test
    fun `equal square root with one action`() {
        val example = "√25+1"
        val operation = "√+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("6", result.calculation)
    }

    @Test
    fun `equal one minus number`() {
        val example = "-5"
        val operation = "-"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("-5", result.calculation)
    }

    @Test
    fun `equal minus number with one action`() {
        val example = "-5+1"
        val operation = "-+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("-4", result.calculation)
    }

    @Test
    fun `equal minus pow with one action`() {
        val example = "5^(0-1)"
        val operation = "^(-)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals("0.2", result.calculation)
    }

    @Test
    fun `equal arcsin`() {
        val example = "asin(0.5)"
        val operation = "asin()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(Math.toDegrees(asin(0.5)), result.calculation.toDouble())
    }

    @Test
    fun `equal arcsin with 1 action`() {
        val example = "asin(0.3 + 0.2)"
        val operation = "asin(+)"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(Math.toDegrees(asin(0.5)), result.calculation.toDouble())
    }

    @Test
    fun `equal zero division exception`() {
        val example = "2/0"
        val operation = "/"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(Double.POSITIVE_INFINITY, result.calculation.toDouble(),)
    }

    @Test
    fun `pi + number`() {
        val example = "π+3"
        val operation = "+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(6.14159265, result.calculation.toDouble(),)
    }

    @Test
    fun `e + number`() {
        val example = "e+3"
        val operation = "+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(5.7182818300000005, result.calculation.toDouble())
    }

    @Test
    fun `sin(30)`() {
        val example = "sin(30)"
        val operation = "sin()"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(0.5, result.calculation.toDouble())
    }

    @Test
    fun `sin + number`() {
        val example = "sin(30)+3"
        val operation = "sin()+"
        val history = ""

        val result = equal.equal(example, operation, history)

        assertEquals(3.5, result.calculation.toDouble())
    }

}