package com.example.mcdonaldsclone.features.makeOrder

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepperTopAppBar(
    currentStep: Int, // 0, 1, or 2
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit
) {
    val steps = listOf("Wybierz", "Potwierdź i zapłać", "Odbierz")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {onBackClick()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Wróć"
                    )
                }
            },
            title = { Text(text = "") },
            scrollBehavior = scrollBehavior,
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, label ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = label,
                        fontWeight = if (index == currentStep) FontWeight.Bold else FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Punkt
                    Canvas(modifier = Modifier.size(10.dp)) {
                        drawCircle(
                            color = when (index) {
                                currentStep -> Color(0xFFFFC107) // Żółty
                                else -> Color.LightGray
                            }
                        )
                    }
                }

                // Linia między punktami
                if (index < steps.lastIndex) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .align(Alignment.CenterVertically),
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}
