package com.example.calculator_compose.domain.model

data class PresentationValues(val calculation: String, val action: String)

data class DomainAllValues(val calculation: String, val action: String, val history: String)

data class DomainValues(val numbers: MutableList<String>, val action: MutableList<String>)



