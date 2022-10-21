package com.example.calculator_compose.domain.model

data class Values(
    val calculation: String, val action: String
)

data class AllValues(
    val calculation: String,
    val action: String,
    val history: String,
)

