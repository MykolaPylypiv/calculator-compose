package com.example.calculator_compose.core

interface Mapper<S, R> {

    fun map(data: S): R
}