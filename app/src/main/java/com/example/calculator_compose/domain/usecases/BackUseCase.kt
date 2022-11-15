package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.app.Strings
import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface BackUseCase {

    fun back(example: String, action: String): PresentationValues

    class Base @Inject constructor() : BackUseCase {

        private val startExample = Strings.START_EXAMPLE
        private val empty = Strings.EMPTY

        override fun back(example: String, action: String) =
            if (example.length == 1 || example == startExample) PresentationValues(
                calculation = startExample, action = empty
            )
            else if (action != empty && example.last() == action.last()) PresentationValues(
                example.dropLast(n = 1), action.dropLast(n = 1)
            )
            else PresentationValues(example.dropLast(n = 1), action)
    }
}