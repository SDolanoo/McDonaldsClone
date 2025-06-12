package com.example.mcdonaldsclone.features.mojeM

import android.R.attr.category
import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.app.SmallCard
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.features.home.FakePromo
import com.example.mcdonaldsclone.features.home.PromoTextCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MojeMScreen(
    onNavigateToCoupons: () -> Unit
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@Composable
fun ScrollContent(
    innerPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CardQR(
                modifier = Modifier,
                topText = "M 408 465",
                bottomText = "Zeskanuj kod, aby zbierać punkty"
            )
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Row {
                    Text(
                        text = "MojeM Nagrody",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Zobacz wiecej ->",
                        style = MaterialTheme.typography.titleSmall,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue
                    )
                }


                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SmallCard(
                        icon = Icons.Default.Star,
                        text = "Mała karta"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SmallCard(
                        icon = Icons.Default.Star,
                        text = "Mała karta"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SmallCard(
                        icon = Icons.Default.Star,
                        text = "Mała karta"
                    )

                }
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        FakeDataProvider.coupons.forEach { coupon ->
            item{
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = "MojeM okazYEAH!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )


                    Spacer(modifier = Modifier.height(12.dp))

                    OkazYeahCard(
                        title = coupon.title,
                        category = coupon.category,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

    }
}

@Composable
fun CardQR(
    modifier: Modifier = Modifier,
    topText: String,
    bottomText: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB3B)), // żółte tło
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Środkowy zielony box zamiast grafiki
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2E7D32))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = topText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tekst na dole karty
            Text(
                text = bottomText,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun LoyaltyCards(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {})
{
    Card(
        modifier = modifier
            .size(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF2E7D32)) // Placeholder image
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun OkazYeahCard(
    title: String,
    category: String,
    modifier: Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF2E7D32)) // Placeholder image
            )

            Text(
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium,
                text = title
            )
        }
    }
}
