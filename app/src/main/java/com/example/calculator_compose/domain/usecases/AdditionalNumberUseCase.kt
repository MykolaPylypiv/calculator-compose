package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface AdditionalNumberUseCase {

    fun letterNum(text: String, example: String, action: String): String

    class AdditionalNumber @Inject constructor() : AdditionalNumberUseCase {

        private val zero = Strings.NUMBER_ZERO
        private val one = Strings.NUMBER_ONE
        private val two = Strings.NUMBER_TWO
        private val three = Strings.NUMBER_THREE
        private val four = Strings.NUMBER_FOUR
        private val five = Strings.NUMBER_FIVE
        private val six = Strings.NUMBER_SIX
        private val seven = Strings.NUMBER_SEVEN
        private val eight = Strings.NUMBER_EIGHT
        private val nine = Strings.NUMBER_NINE
        private val e = Strings.NUMBER_E
        private val pi = Strings.NUMBER_P
        private val empty = Strings.EMPTY


        override fun letterNum(text: String, example: String, action: String): String {
            val xExample = example.last().toString()

            if (example == Strings.START_EXAMPLE) return text

            if (xExample != zero && xExample != one && xExample != two && xExample != three && xExample != four && xExample != five && xExample != six && xExample != seven && xExample != eight && xExample != nine && xExample != e && xExample != pi) return example + text

            if (example.dropLast(n = 1) != empty && example[example.length - 2] != action.lastOrNull()) return example + text

            return example
        }
    }
}