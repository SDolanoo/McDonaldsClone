package com.example.mcdonaldsclone.features.cart.composables

import android.R.attr.onClick
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.ZestawOption
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimaps.index
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.RequestBuilder.options

@Composable
fun ChooseZestawContent(
    innerPadding: PaddingValues,
    productId: Long
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val product: MenuItem = FakeDataProvider.menuItems.find { it.id == productId }!!

    var currentName by remember { mutableStateOf("Zestaw") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Column {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = currentName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    if (selectedIndex == 0) {
                        Text(
                            text = "%.2f zł".format(product.setPrice),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    } else {
                        Text(
                            text = "%.2f zł".format(product.setPrice!!.plus(3.00)),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }

                }
                Image(
                    painter = painterResource(id = product.imageResId), // swap image!
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        item {
            Text(
                text = "Wybierz rozmiar zestawu",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            ZestawOptionItem(
                imageResId = product.imageResId,
                name = "${product.name} Zestaw",
                price = product.basePrice,
                priceDifference = "-3,00zł",
                selected = 0 == selectedIndex,
                onClick = {
                    currentName = "Zestaw"
                    selectedIndex = 0
                }
            )
        }
        item {
            ZestawOptionItem(
                imageResId = product.imageResId,
                name = "${product.name} Zestaw Powiększony",
                price = product.basePrice,
                priceDifference = "+3,00zł",
                selected = 1 == selectedIndex,
                onClick = {
                    currentName = "Zestaw Powiększony"
                    selectedIndex = 1
                }
            )
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
@Composable
private fun ZestawOptionItem(
    imageResId: Int,
    name: String,
    price: Double,
    priceDifference: String,
    selected: Boolean,
    onClick: () -> Unit
) { // TODO Make zestaw class with fake data provider - name, additional price lista produktów ?
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "title",
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )
            if (!selected) {
                Text(
                    text = priceDifference,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }


        }

        if (selected) {
            Box(
                modifier = Modifier
                    .size(24.dp) // mały rozmiar kółka
                    .background(color = Color(0xFFFFC107), shape = CircleShape)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Wybrano",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp) // mniejszy check w środku
                )
            }
        }
    }
}