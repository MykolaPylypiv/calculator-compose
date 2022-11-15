package com.example.calculator_compose.domain.calculation.calculation

import javax.inject.Inject
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.pow

interface PrimitiveCalculation {

    fun plus(num: Double, num1: Double): Double

    fun minus(num: Double, num1: Double): Double

    fun multiply(num: Double, num1: Double): Double

    fun division(num: Double, num1: Double): Double

    fun percent(num: Double, num1: Double): Double

    fun power(num: Double, num1: Double): Double

    fun sqrt(num: Double): Double

    fun sin(num: Double): Double

    fun cos(num: Double): Double

    fun tan(num: Double): Double

    fun arcSin(num: Double): Double

    fun arcCos(num: Double): Double

    fun arcTan(num: Double): Double

    fun lg(num: Double): Double

    fun ln(num: Double): Double

    fun factorial(num: Int): Int

    fun numPi(): Double

    fun numE(): Double

    class Base @Inject constructor() : PrimitiveCalculation {

        override fun plus(num: Double, num1: Double) = num + num1

        override fun minus(num: Double, num1: Double) = num - num1

        override fun multiply(num: Double, num1: Double) = num * num1

        override fun division(num: Double, num1: Double) = num / num1

        override fun percent(num: Double, num1: Double) = num % num1

        override fun power(num: Double, num1: Double) = num.pow(num1)

        override fun sqrt(num: Double) = kotlin.math.sqrt(num)

        override fun sin(num: Double) = kotlin.math.sin(num)

        override fun cos(num: Double) = kotlin.math.cos(num)

        override fun tan(num: Double) = kotlin.math.tan(num)

        override fun arcSin(num: Double) = Math.toDegrees(asin(num))

        override fun arcCos(num: Double) = Math.toDegrees(acos(num))

        override fun arcTan(num: Double) = Math.toDegrees(atan(num))

        override fun lg(num: Double) = kotlin.math.log10(num)

        override fun ln(num: Double) = kotlin.math.ln(num)

        override fun factorial(num: Int) = (1..num).reduce(Int::times)

        override fun numPi() = Math.PI

        override fun numE() = Math.E

    }
}