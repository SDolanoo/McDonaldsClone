package com.example.mcdonaldsclone.features.mojeM

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.features.QRCode.QRCodeViewModel
import com.example.mcdonaldsclone.features.loyalty.LoyaltyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MojeMScreen(
    onNavigateToCoupons: (Long) -> Unit,
    onNavigateToLoyalty: () -> Unit,
    onNavigateToQR: () -> Unit,
    viewModel: QRCodeViewModel
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.icon5),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
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
            onNavigateToLoyalty = { onNavigateToLoyalty() },
            onNavigateToQR = { onNavigateToQR() },
            viewModel = viewModel
        )
    }
}

@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    onNavigateToCoupons: (Long) -> Unit,
    onNavigateToLoyalty: () -> Unit,
    onNavigateToQR: () -> Unit,
    viewModel: QRCodeViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CardQR(
                modifier = Modifier,
                topText = "M 408 465",
                bottomText = "Zeskanuj kod, aby zbierać punkty",
                onClick = { onNavigateToQR() },
                viewModel = viewModel
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
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
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
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
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
                                .size(width = 120.dp, height = 180.dp)
                        ) {
                            IconButton(
                                onClick = { onNavigateToLoyalty() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = Color(0xFFFFC000),
                                        shape = CircleShape
                                    )
                                    .padding(top = 12.dp)
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
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 12.sp
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
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
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
    bottomText: String,
    onClick: () -> Unit,
    viewModel: QRCodeViewModel
) {
    val qrBitmap = remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(Unit) {
        qrBitmap.value = viewModel.generateQRCodeBitmap()
    }

    val code by viewModel.code

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC000)), // żółte tło
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
                Card(
                    modifier = Modifier
                        .height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        qrBitmap.value?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "QR Code",
                                modifier = Modifier.size(150.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = code,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
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



