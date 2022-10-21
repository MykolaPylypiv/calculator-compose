package com.example.calculator_compose.domain.usecases

import com.example.calculator_compose.domain.model.Values
import javax.inject.Inject

interface BackUseCase {

    fun back(example: String, action: String): Values

    class Base @Inject constructor() : BackUseCase {

        override fun back(example: String, action: String): Values =
            if (example.length == 1 || example == "null")
                Values("null", "")
            else if (action != "" && example.last() == action.last())
                Values(
                    example.substring(0, example.length - 1),
                    action.substring(0, action.length - 1)
                )
            else
                Values(
                    example.substring(0, example.length - 1),
                    action
                )
    }
}