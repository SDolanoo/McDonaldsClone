package com.example.mcdonaldsclone.features.cart.composables

import android.R
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import com.example.mcdonaldsclone.core.database.model.Set
import org.apache.commons.math3.stat.StatUtils.product

@Composable
fun ChooseZestawFinalStepContent(
    innerPadding: PaddingValues,
    productId: Long,
) {

    val product: MenuItem = FakeDataProvider.menuItems.find { it.id == productId }!!
    val frytki: MenuItem = FakeDataProvider.menuItems.find { it.id == 108L }!!
    val sos: MenuItem = FakeDataProvider.menuItems.find { it.id == 202L }!!
    val napoj: MenuItem = FakeDataProvider.menuItems.find { it.id == 101L }!!
    val zestaw = Set(
        listMenuItems = listOf(product,frytki,sos,napoj),
        imageResId = product.imageResId,
        price = product.setPrice!!,
        quantity = 1
    )


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
                            text = product.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Text(
                        text = "%.2f zł".format(zestaw.price),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start).padding(start = 16.dp)
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
                    Text(text = "Zmień rozmiar posiłku", color = Color.Black)
                }
            }
        }

        items(zestaw.listMenuItems) { product ->
            ProductOption(
                product = product,
                onClick = {}
            )
        }
    }
}

@Composable
private fun ProductOption(
    product: MenuItem,
    onClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Sauce image
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Sauce name
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Button(
            onClick = { onClick(product.id) },
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(10))
                .clip(RoundedCornerShape(10))
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Zmień", color = Color.Black)
        }

    }
}