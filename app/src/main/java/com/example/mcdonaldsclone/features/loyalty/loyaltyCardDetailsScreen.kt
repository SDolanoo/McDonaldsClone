package com.example.mcdonaldsclone.features.loyalty

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem


@Composable
fun LoyaltyCardDetailsScreen(
    loyaltyItemId: Long,
    popBack: () -> Unit
) {
    val item: LoyaltyItem = requireNotNull(FakeDataProvider.loyaltyItems.find { it.id == loyaltyItemId })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(item.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            IconButton(
                onClick = { popBack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Wróć",
                    tint = Color.White
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Tytuł i punkty
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Brush.horizontalGradient(listOf(Color.Magenta, Color(0xFFFFA500))))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item.points.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Opis
            Text(
                text = "Wymieniaj punkty na pyszne nagrody z oferty klasycznej. Oferta nie obowiązuje w restauracjach zlokalizowanych na terenie lotn...",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Zasady
            Text(
                text = "Zasady",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Dla zalogowanych użytkowników")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Data ważności
            Text(text = "Wygasa 2025-07-17")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Regulamin usług online",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                modifier = Modifier.clickable { /* TODO */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Jak to działa
            OutlinedButton(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Jak to działa?")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Przycisk Odbierz
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Odbierz")
        }
    }
}
