package com.example.calculator_compose.domain.calculation.result

import com.example.calculator_compose.app.Strings
import javax.inject.Inject

interface SelectSection {

    fun selectSection(example: String): String

    class Base @Inject constructor() : SelectSection {
        override fun selectSection(example: String): String {
            var sectionExample = example
            var start: Int
            var end = 0

            while (sectionExample.contains(Strings.LEFT_BRACKET)) {
                start = sectionExample.indexOf(Strings.LEFT_BRACKET) + 1

                if (sectionExample.contains(Strings.RIGHT_BRACKET)) {
                    end = sectionExample.indexOf(Strings.RIGHT_BRACKET)
                    sectionExample = sectionExample.substring(start, end)
                } else {
                    end = example.length
                    sectionExample = sectionExample.substring(start, sectionExample.length)
                }
            }

            return if (end == 0) example else sectionExample
        }
    }
}