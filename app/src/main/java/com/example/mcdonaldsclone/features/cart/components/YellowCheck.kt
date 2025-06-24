package com.example.mcdonaldsclone.features.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun YellowCheck() {
    Box(
        modifier = Modifier
            .size(24.dp) // mały rozmiar kółka
            .background(color = Color(0xFFFFC107), shape = CircleShape)
            .border(width = 1.dp, color = Color.Black, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Wybrano",
            tint = Color.Black,
            modifier = Modifier.size(16.dp) // mniejszy check w środku
        )
    }
}
