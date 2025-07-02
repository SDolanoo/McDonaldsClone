package com.example.mcdonaldsclone.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem

@Composable
fun ProductDetailsContent(
    innerPadding: PaddingValues,
    menuItemId: Long,
    onQuantity: (Int) -> Unit,
    ) {
    val product: MenuItem = FakeDataProvider.menuItems.find { it.id == menuItemId }!!
    var quantity by remember { mutableIntStateOf(1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        product.let {
            item {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            item {
                Text(
                    text = "%.2f zł".format(it.basePrice),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }

            item {
                Image(
                    painter = painterResource(id = it.imageResId), // Replace with real image
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            item {
                Spacer(Modifier.height(3.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    QuantitySelector(
                        quantity = quantity,
                        onIncrease = { quantity++; onQuantity(quantity) },
                        onDecrease = { if (quantity > 1) quantity--; onQuantity(quantity) }
                    )
                }
            }


            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = { /* TODO: Navigate to details */ },
                        modifier = Modifier
                            .border(1.dp, Color.Gray, RoundedCornerShape(10))
                            .clip(RoundedCornerShape(10))
                            .height(60.dp)
                            .background(Color.White)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Składniki i wartości odżywcze", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
private fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        IconButton(onClick = onDecrease) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Zmniejsz ilość", tint = Color.Gray)
        }

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.Black
        )

        IconButton(onClick = onIncrease) {
            Icon(Icons.Default.Add, contentDescription = "Zwiększ ilość", tint = Color.Black)
        }
    }
}