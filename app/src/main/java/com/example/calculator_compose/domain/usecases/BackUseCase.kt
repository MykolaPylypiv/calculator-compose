package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.PresentationValues
import javax.inject.Inject

interface BackUseCase {

    fun back(example: String, action: String): PresentationValues

    class Base @Inject constructor() : BackUseCase {

        override fun back(example: String, action: String): PresentationValues =
            if (example.length == 1 || example == "null")
                PresentationValues("null", "")
            else if (action != "" && example.last() == action.last())
                PresentationValues(
                    example.dropLast(1),
                    action.dropLast(1)
                )
            else
                PresentationValues(
                    example.dropLast(1),
                    action
                )
    }
}