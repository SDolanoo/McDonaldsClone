package com.example.mcdonaldsclone.features.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.composables.SmallCard


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onNavigateToCoupons: () -> Unit,
    onNavigateToQR: () -> Unit,
    onNavigateToZamowIOdbierz: () -> Unit,
    onNavigateToMojeM: () -> Unit
    ) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
                title = {
                    Image(
                        painter = painterResource(R.drawable.icon6),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(48.dp)
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.icon4),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable { onNavigateToQR() }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.promo6),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFF8A0000))
                )
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = "Czego dziś szukasz?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SmallCard(
                            imageResId = R.drawable.icon3,
                            text = "okazYeah!",
                            onClick = { onNavigateToCoupons() }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        SmallCard(
                            imageResId = R.drawable.icon2,
                            text = "MojeM Nagrody",
                            onClick = { onNavigateToMojeM() }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        SmallCard(
                            imageResId = R.drawable.icon,
                            text = "Zamów i odbierz",
                            onClick = { onNavigateToZamowIOdbierz() }
                        )

                    }
                }
            }

            FakeDataProvider.promos.forEach { promo ->
                item{
                    PromoTextCard(
                        title = promo.title,
                        subtitle = promo.subtitle,
                        titleColor = promo.textColor,
                        textBackgroundColor = promo.textBackgroundColor,
                        image = promo.imageResId
                    )
                }
            }

        }
    }
}

