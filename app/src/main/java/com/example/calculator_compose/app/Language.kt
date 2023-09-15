package com.example.calculator_compose.app

data class Language(
    val deleteHistoryDialog: String,
    val changeThemeDialog: String,

    val dismissButton: String,
    val acceptButton: String,

    val topBarSettingsTheme: String,

    val textColor: String,
    val secondaryTextColor: String,
    val tertiaryTextColor: String,
    val buttonColor: String,
    val secondaryButtonColor: String,
    val historyColor: String,
    val backgroundColor: String,

    val darkButton: String,
    val lightButton: String,

    val moreButton: String,

    val whiteButton: String,
    val grayButton: String,
    val blackButton: String,

    val toastChangeLanguage: String,
    val toastChangeTheme: String,

    val dialogDeleteHistoryAccept: String,
    val dialogDeleteHistoryDismiss: String,
)

val english = Language(
    deleteHistoryDialog = "Are you sure you want to delete the history ?",
    changeThemeDialog = "Select a theme",

    dismissButton = "Cancel",
    acceptButton = "Accept",

    topBarSettingsTheme = "Settings theme",

    textColor = "Select text color",
    secondaryTextColor = "Select secondary text color",
    tertiaryTextColor = "Select tertiary text color",
    buttonColor = "Select button color",
    secondaryButtonColor = "Select secondary button color",
    historyColor = "Select history color",
    backgroundColor = "Select background color",

    darkButton = "Dark",
    lightButton = "Light",

    moreButton = "More...",

    whiteButton = "White",
    grayButton = "Gray",
    blackButton = "Black",

    toastChangeLanguage = "The language will change after restart",
    toastChangeTheme = "Theme will change after restart",

    dialogDeleteHistoryAccept = "Accept",
    dialogDeleteHistoryDismiss = "Cancel",
)

val ukrainian = Language(
    deleteHistoryDialog = "Ви впевнені, що хочете видалити історію обчислень ?",
    changeThemeDialog = "Оберіть тему",

    dismissButton = "Відмінити",
    acceptButton = "Підтвердити",

    topBarSettingsTheme = "Налаштування теми",

    textColor = "Виберіть колір тексту",
    secondaryTextColor = "Виберіть вторинний колір тексту",
    tertiaryTextColor = "Виберіть третинний колір тексту",
    buttonColor = "Виберіть колір кнопки",
    secondaryButtonColor = "Виберіть вторинний колір кнопки",
    historyColor = "Виберіть колір історії",
    backgroundColor = "Виберіть колір фону",

    darkButton = "Темний",
    lightButton = "Світлий",

    moreButton = "Більше...",

    whiteButton = "Білий",
    grayButton = "Сірий",
    blackButton = "Чорний",

    toastChangeLanguage = "Мова зміниться після перезапуску",
    toastChangeTheme = "Тема зміниться після перезапуску",

    dialogDeleteHistoryAccept = "Так",
    dialogDeleteHistoryDismiss = "Ні",
)