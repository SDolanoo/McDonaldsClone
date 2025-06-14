package com.example.mcdonaldsclone.features.home

import android.R.attr.subtitle
import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.app.SmallCard
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.Category

data class Promo(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageColor: Color,
    val textColor: Color,
    val textBackgroundColor: Color
)

object FakePromo{
    val promos = listOf(
        Promo(1,
            "Nowe McFlurry Pistacjowe.",
            "Spróbuj i poczuj, o co to zamieszanie!",
            Color(0xFF7ed341),
            Color(0xFF000000),
            Color(0xFFb0780a)),
        Promo(2,
            "Kiedy masz smaka na Maka...",
            "...zamów McRoyal Cheesy Jalapeno Bacon z serowym sosem Smoky Cheddar i papryczkamy jalapeno!",
            Color(0xFF4A9123),
            Color(0xFFFFFFFF),
            Color(0xFF7A694A)
        ),
        Promo(3,
            "Odkryj nową Kokosową Iced Latte!",
            "Spróbuj też z bitą śmietanką",
            Color(0xFF85EAB9),
            Color(0xFF000000),
            Color(0xFFD0B783)
        ),
        Promo(4,
            "Dodatkową superopcją w Super Combo!",
            "McNuggets polecają się do Twojego superzestawu!",
            Color(0xFF0C540D),
            Color(0xFFFFFFFF),
            Color(0xFF317332)
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onNavigateToCoupons: () -> Unit,
    ) {
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
                        "QR tu",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Text(
                        text = "M",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
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
                            icon = Icons.Default.Star,
                            text = "MojeM",
                            onClick = { onNavigateToCoupons() }
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

            FakePromo.promos.forEach { promo ->
                item{
                    PromoTextCard(
                        title = promo.title,
                        subtitle = promo.subtitle,
                        titleColor = promo.textColor,
                        textBackgroundColor = promo.textBackgroundColor,
                        imageColor = promo.imageColor
                    )
                }
            }

        }
    }
}

@Composable
fun PromoTextCard(
    title: String,
    subtitle: String,
    titleColor: Color = Color.White,
    textBackgroundColor: Color = Color.Black,
    imageColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var titleYInRoot by remember { mutableStateOf(0f) }
    var cardYInRoot by remember { mutableStateOf(0f) }
    var textHeightPx by remember { mutableStateOf(0) }

    val density = LocalDensity.current
    val shape = RoundedCornerShape(16.dp)

    // wysokość karty = wysokość Column + 200.dp
    val cardHeightDp by remember {
        derivedStateOf {
            with(density) { textHeightPx.toDp() + 200.dp }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeightDp)
            .padding(24.dp)
            .clickable { onClick() }
            .onGloballyPositioned { cardCoords ->
                cardYInRoot = cardCoords.positionInRoot().y
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.clip(shape)) {
            // Background "image"
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(imageColor)
            )

            // Gradient
            val titleYRelative = titleYInRoot - cardYInRoot
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                textBackgroundColor.copy(alpha = 0.9f)
                            ),
                            startY = titleYRelative + 70f,
                            endY = titleYRelative + 150f
                        )
                    )
            )

            // Text block
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .onGloballyPositioned { coords ->
                        textHeightPx = coords.size.height
                    }
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.onGloballyPositioned { coords ->
                        titleYInRoot = coords.positionInRoot().y
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    color = titleColor,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
