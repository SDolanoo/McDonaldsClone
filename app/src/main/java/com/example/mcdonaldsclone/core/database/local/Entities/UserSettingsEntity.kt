package com.example.mcdonaldsclone.core.database.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_settings")
data class UserSettingsEntity(
    @PrimaryKey val id: Int = 0,
    val isDarkMode: Boolean,
    val preferredLanguage: String
)
