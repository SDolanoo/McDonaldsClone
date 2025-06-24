package com.example.mcdonaldsclone.features.cart.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
    color: Color = Color(0xFFFFCC00)
) {

    OutlinedButton(
        onClick = { onClick()},
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RectangleShape
    ) {
        Text(text)
    }

}