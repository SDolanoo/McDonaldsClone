package com.example.mcdonaldsclone.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.mcdonaldsclone.core.navigation.AppNavGraph
import com.example.mcdonaldsclone.ui.theme.McDonaldsCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContent {
//            val navController = rememberNavController()
//            AppNavGraph(
//                navController = navController
//            )
//        }
        setContent {
            MaterialTheme {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        FullScreenImageCardPreview(
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                        )
                    }

                    item {
                        SmallCard(
                            icon = Icons.Default.Star,
                            text = "Mała karta"
                        )
                    }

                    item {
                        ImageOverlayTextCard(
                            title = "Karta testowa",
                            titleColor = Color.White,
                            overlayColor = Color.Black.copy(alpha = 0.6f),
                            onClick = TODO()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SmallCard(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
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
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun okazYeahCard(
    title: String,
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

@Composable
fun ImageOverlayTextCard(
    title: String,
    titleColor: Color = Color.White,
    overlayColor: Color = Color.Black,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {
            // Background image or placeholder
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2E7D32)) // Placeholder image
            )

            // Gradient overlay at the bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, overlayColor),
                            startY = 300f, // bottom
                            endY = 375f      // approx. middle
                        )
                    )
            )

            // Text at bottom
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun FullScreenImageCardPreview(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2E7D32))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardShowcasePreview() {
    MaterialTheme {
        val navController = rememberNavController()
        AppNavGraph(
            navController = navController
        )
    }
}

@Composable
fun CardShowcaseScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ImageOverlayTextCard(
                title = "Karta testowa",
                titleColor = Color.White,
                overlayColor = Color.Black.copy(alpha = 0.9f)
            )
        }

        item {
            okazYeahCard(
                title = "Test Lorem Impsum dolor sit amet",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
        }

        item {
            SmallCard(
                icon = Icons.Default.Star,
                text = "Mała karta"
            )
        }


    }
}

