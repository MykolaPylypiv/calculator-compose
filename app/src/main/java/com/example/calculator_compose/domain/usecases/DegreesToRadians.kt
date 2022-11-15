package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface DegreesToRadians {

    fun converting(): String

    class Base @Inject constructor() : DegreesToRadians {

        private val deg = Strings.DEGREES
        private val rad = Strings.RADIANS

        override fun converting(): String {
            radians = !radians
            return if (radians) rad else deg
        }
    }

    companion object {

        var radians = true
    }

}