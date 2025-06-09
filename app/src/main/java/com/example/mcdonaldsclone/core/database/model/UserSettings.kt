package com.example.mcdonaldsclone.core.database.model

data class UserSettings(
    val id: Int = 0,
    val isDarkMode: Boolean,
    val preferredLanguage: String
)