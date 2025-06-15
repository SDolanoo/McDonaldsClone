package com.example.mcdonaldsclone.features.mojeM

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.features.loyalty.LoyaltyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MojeMScreen(
    onNavigateToCoupons: (Long) -> Unit,
    onNavigateToLoyalty: () -> Unit
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
                        "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Text(
                        text = "MojeM okazje",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Text(
                        text = "${FakeDataProvider.loyaltyPoints.currentPoints} pkt",
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        ScrollContent(
            innerPadding,
            onNavigateToCoupons = { couponId ->  onNavigateToCoupons(couponId) },
            onNavigateToLoyalty = { onNavigateToLoyalty() }
        )
    }
}

@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    onNavigateToCoupons: (Long) -> Unit,
    onNavigateToLoyalty: () -> Unit
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = FakeDataProvider.loyaltyItems.take(4)) { loyaltyItem ->
                        LoyaltyCard(
                            text = "${loyaltyItem.title}\n${loyaltyItem.points} pkt",
                            image = loyaltyItem.imageResId,
                            onClick = { onNavigateToLoyalty() }
                        )
                    }

                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(width = 160.dp, height = 180.dp)
                        ) {
                            IconButton(
                                onClick = { onNavigateToLoyalty() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = Color(0xFF2E7D32),
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Zobacz więcej",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Zobacz więcej",
                                style = MaterialTheme.typography.labelMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "MojeM okazYEAH!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        items(FakeDataProvider.coupons) { coupon ->
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                OkazYeahCard(
                    title = coupon.title,
                    category = coupon.category,
                    image = coupon.imageResId,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    onClick = { onNavigateToCoupons(coupon.id) }
                )
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



