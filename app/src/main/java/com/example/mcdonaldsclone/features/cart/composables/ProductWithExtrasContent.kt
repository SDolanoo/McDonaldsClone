package com.example.mcdonaldsclone.features.cart.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.example.mcdonaldsclone.features.cart.components.YellowCheck

@Composable
fun ProductWithExtrasContent(
    innerPadding: PaddingValues,
    menuItemId: Long,
    onSelectedIndex: (Long) -> Unit
) {
    var selectedIndex by remember { mutableLongStateOf(202L) }



    val product: MenuItem = FakeDataProvider.menuItems.find { it.id == menuItemId }!!

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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "%.2f zł".format(product.basePrice),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }

                Image(
                    painter = painterResource(id = product.imageResId), // Replace!
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        item {
            Text(
                text = "Wybierz sos",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        items(FakeDataProvider.menuItems.filter { it.subCategoryId == if (product.subCategoryId == 7L) 11L else 10L }) { sauce ->
            SauceItem(
                sauce = sauce,
                selected = sauce.id == selectedIndex,
                onClick = { selectedIndex = sauce.id; onSelectedIndex(sauce.id) }
            )
        }
    }
}
@Composable
private fun SauceItem(
    sauce: MenuItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Sauce image
        Image(
            painter = painterResource(id = sauce.imageResId),
            contentDescription = sauce.name,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Sauce name
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = sauce.name,
                style = MaterialTheme.typography.bodyLarge
            )
            if (selected) {
                Text(
                    text = "+${"%.2f zł".format(sauce.basePrice)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        if (selected) {
            YellowCheck()
        }
    }
}