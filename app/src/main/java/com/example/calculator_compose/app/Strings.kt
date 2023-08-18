package com.example.calculator_compose.app

object Strings {
    const val EMPTY = ""
    const val SPACE = " "

    const val START_EXAMPLE = "0"

    const val NUMBER_P = "π"
    const val NUMBER_E = "e"

    const val NUMBER_ZERO = "0"
    const val NUMBER_ONE = "1"
    const val NUMBER_TWO = "2"
    const val NUMBER_THREE = "3"
    const val NUMBER_FOUR = "4"
    const val NUMBER_FIVE = "5"
    const val NUMBER_SIX = "6"
    const val NUMBER_SEVEN = "7"
    const val NUMBER_EIGHT = "8"
    const val NUMBER_NINE = "9"

    const val ACTION_MINUS = "-"
    const val ACTION_PLUS = "+"
    const val ACTION_MULTIPLY = "x"
    const val ACTION_DIVISION = "÷"
    const val ACTION_PERCENT = "%"
    const val ACTION_POW = "^"

    const val ACTION_FACTORIAL = "!"
    const val ACTION_SQUARE_ROOT = "√"

    const val ACTION_SIN = "sin("
    const val ACTION_COS = "cos("
    const val ACTION_TAN = "tan("
    const val ACTION_LG = "lg("
    const val ACTION_LN = "ln("

    const val ACTION_ARCSIN = "asin("
    const val ACTION_ARCCOS = "acos("
    const val ACTION_ARCTAN = "atan("

    const val LEFT_BRACKET = "("
    const val RIGHT_BRACKET = ")"

    const val POINT = "."

    const val DEGREES = "deg"
    const val RADIANS = "rad"

    const val DATABASE_NAME = "database"

    const val STORE_HISTORY_KEY = "history_calculation"
    const val STORE_HISTORY_PREFERENCES = "historyCalculation"

    const val CONST_NUMBER_PI = "3.14159265"
    const val CONST_NUMBER_E = "2.71828183"

    const val TEXT_TWO_ND = "2nd"
    const val TEXT_ACTION_POW = "x^y"
    const val TEXT_FACTORIAL = "X!"
    const val TEXT_POW_MINUS_ONE = "1/x"
    const val TEXT_CLEAR_CALCULATION = "C"
    const val TEXT_EQUAL = "="

    const val TEXT_DELETE_HISTORY_DIALOG = "Are you sure you want to delete the history ?"
    const val TEXT_CHANGE_THEME_DIALOG = "Select a theme"

    const val TEXT_DISMISS_BUTTON = "Cancel"
    const val TEXT_ACCEPT_BUTTON = "Accept"

    const val TEXT_TOP_BAR_SETTINGS_THEME = "Settings theme"

    const val TEXT_COLOR = "Select text color"
    const val TEXT_SECONDARY_COLOR = "Select secondary text color"
    const val TEXT_TERTIARY_COLOR = "Select tertiary text color"
    const val TEXT_BUTTON_COLOR = "Select button color"
    const val TEXT_SECONDARY_BUTTON_COLOR = "Select secondary button color"
    const val TEXT_HISTORY_COLOR = "Select history color"
    const val TEXT_BACKGROUND_COLOR = "Select background color"

    const val TEXT_DARK_BUTTON = "Dark"
    const val TEXT_LIGHT_BUTTON = "Light"

    const val TEXT_MORE_BUTTON = "More..."

    const val TEXT_WHITE_COLOR_BUTTON = "White"
    const val TEXT_GRAY_BUTTON = "Gray"
    const val TEXT_BLACK_BUTTON = "Black"
}

object Exceptions {
    const val EXCEPTION_NUMBERS_COUNT_EQUAL_ONE = "numbers count == 1 in action with two numbers"
    const val EXCEPTION_THIRD_PRIORITY_NOT_HAVE_ACTION = "third priority not have action"
    const val EXCEPTION_SECOND_PRIORITY_NOT_HAVE_ACTION = "second priority not have action"
    const val EXCEPTION_FIRST_PRIORITY_NOT_HAVE_ACTION = "first priority not have action"
    const val EXCEPTION_LOWEST_PRIORITY_NOT_HAVE_ACTION = "lowest priority not have action"
}