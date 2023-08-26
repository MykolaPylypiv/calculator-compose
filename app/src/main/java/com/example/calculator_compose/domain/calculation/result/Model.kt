package com.example.calculator_compose.domain.calculation.result

data class CalculationValues(
    val action: MutableList<String>,
    val example: String,
    val numbers: MutableList<String>,
    val isRadians: Boolean,
    val radians: String,
)

data class PreparationValues(
    val action: MutableList<String>,
    val example: String,
)