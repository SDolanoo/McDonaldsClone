package com.example.mcdonaldsclone.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import com.example.mcdonaldsclone.features.home.PromoTextCard
import com.example.mcdonaldsclone.features.mojeM.OkazYeahCard
import com.example.mcdonaldsclone.ui.theme.McDonaldsCloneTheme
import com.example.mcdonaldsclone.R

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

//                    item {
//                        SmallCard(
//                            icon = Icons.Default.Star,
//                            text = "Mała karta"
//                        )
//                    }

                    item{
                        PromoTextCard(
                            title = "Test",
                            subtitle = "promo.subtitle",
                            titleColor = Color(0xFF85EAB9),
                            textBackgroundColor = Color(0xFF000000),
                            image = R.drawable.kupon
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SmallCard(
    @DrawableRes imageResId: Int,
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
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
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

//@Composable
//fun CardShowcaseScreen() {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        item{
//            PromoTextCard(
//                title = "Test",
//                subtitle = "promo.subtitle",
//                titleColor = Color(0xFF85EAB9),
//                textBackgroundColor = Color(0xFF000000),
//                imageColor = Color(0xFFD0B783)
//            )
//        }
//
//        item {
//            OkazYeahCard(
//                title = "Test Lorem Impsum dolor sit amet",
//                category = "Hello",
//                modifier = Modifier
//                    .height(250.dp)
//                    .fillMaxWidth()
//            )
//        }
//
//        item {
//            SmallCard(
//                icon = Icons.Default.Star,
//                text = "Mała karta"
//            )
//        }
//
//
//    }
//}
//
