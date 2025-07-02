package com.example.mcdonaldsclone.core.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepperTopAppBar(
    currentStep: Int,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit
) {
    val steps = listOf("Wybierz", "Potwierdź i zapłać", "Odbierz")

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Wróć"
                )
            }
        },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 🔹 GÓRNY RZĄD – Teksty
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    steps.forEachIndexed { index, label ->
                        Text(
                            text = label,
                            fontWeight = if (index == currentStep) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (label == "Potwierdź i zapłać") 12.sp else 14.sp,
                            color = Color.Black,
                            maxLines = 1,
                            modifier = Modifier.weight(1f),
                            textAlign = if (index == 0) {TextAlign.Start} else if (index == 2) {TextAlign.End} else {TextAlign.Center}
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // 🔸 DOLNY RZĄD – Kropki i linie
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    steps.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(modifier = Modifier.size(8.dp)) {
                                drawCircle(
                                    color = if (index <= currentStep) Color(0xFFFFC107) else Color.LightGray
                                )
                            }
                        }

                        if (index < steps.lastIndex) {
                            Divider(
                                color = if (index + 1 <= currentStep) Color(0xFFFFC107) else Color.LightGray,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                            )
                        }
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}
