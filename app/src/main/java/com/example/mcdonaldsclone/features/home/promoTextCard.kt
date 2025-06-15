package com.example.mcdonaldsclone.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PromoTextCard(
    title: String,
    subtitle: String,
    titleColor: Color = Color.White,
    textBackgroundColor: Color = Color.Black,
    image: Int,
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
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
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
