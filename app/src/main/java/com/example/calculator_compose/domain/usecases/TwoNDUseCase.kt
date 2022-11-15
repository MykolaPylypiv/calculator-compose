package com.example.calculator_compose.domain.usecases

import javax.inject.Inject

interface TwoNDUseCase {

    fun change(boolean: Boolean): Boolean

    class Base @Inject constructor() : TwoNDUseCase {

        override fun change(boolean: Boolean) = !boolean
    }
}