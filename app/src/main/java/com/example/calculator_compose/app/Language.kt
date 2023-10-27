package com.example.calculator_compose.app


sealed class Language(
) {
    abstract val deleteHistoryDialog: String
    abstract val changeThemeDialog: String

    abstract val dismissButton: String
    abstract val acceptButton: String

    abstract val topBarSettingsTheme: String

    abstract val textColor: String
    abstract val secondaryTextColor: String
    abstract val tertiaryTextColor: String
    abstract val buttonColor: String
    abstract val secondaryButtonColor: String
    abstract val historyColor: String
    abstract val backgroundColor: String

    abstract val darkButton: String
    abstract val lightButton: String

    abstract val moreButton: String

    abstract val whiteButton: String
    abstract val grayButton: String
    abstract val blackButton: String

    abstract val toastChangeLanguage: String
    abstract val toastChangeTheme: String

    abstract val dialogDeleteHistoryAccept: String
    abstract val dialogDeleteHistoryDismiss: String

    object English : Language() {
        override val deleteHistoryDialog = "Are you sure you want to delete the history ?"
        override val changeThemeDialog = "Select a theme"

        override val dismissButton = "Cancel"
        override val acceptButton = "Accept"

        override val topBarSettingsTheme = "Settings theme"

        override val textColor = "Select text color"
        override val secondaryTextColor = "Select secondary text color"
        override val tertiaryTextColor = "Select tertiary text color"
        override val buttonColor = "Select button color"
        override val secondaryButtonColor = "Select secondary button color"
        override val historyColor = "Select history color"
        override val backgroundColor = "Select background color"

        override val darkButton = "Dark"
        override val lightButton = "Light"

        override val moreButton = "More..."

        override val whiteButton = "White"
        override val grayButton = "Gray"
        override val blackButton = "Black"

        override val toastChangeLanguage = "The language will change after restart"
        override val toastChangeTheme = "Theme will change after restart"

        override val dialogDeleteHistoryAccept = "Accept"
        override val dialogDeleteHistoryDismiss = "Cancel"
    }

    object Ukrainian : Language() {
        override val deleteHistoryDialog = "Ви впевнені, що хочете видалити історію обчислень ?"
        override val changeThemeDialog = "Оберіть тему"

        override val dismissButton = "Відмінити"
        override val acceptButton = "Підтвердити"

        override val topBarSettingsTheme = "Налаштування теми"

        override val textColor = "Виберіть колір тексту"
        override val secondaryTextColor = "Виберіть вторинний колір тексту"
        override val tertiaryTextColor = "Виберіть третинний колір тексту"
        override val buttonColor = "Виберіть колір кнопки"
        override val secondaryButtonColor = "Виберіть вторинний колір кнопки"
        override val historyColor = "Виберіть колір історії"
        override val backgroundColor = "Виберіть колір фону"

        override val darkButton = "Темний"
        override val lightButton = "Світлий"

        override val moreButton = "Більше..."

        override val whiteButton = "Білий"
        override val grayButton = "Сірий"
        override val blackButton = "Чорний"

        override val toastChangeLanguage = "Мова зміниться після перезапуску"
        override val toastChangeTheme = "Тема зміниться після перезапуску"

        override val dialogDeleteHistoryAccept = "Так"
        override val dialogDeleteHistoryDismiss = "Ні"
    }
}