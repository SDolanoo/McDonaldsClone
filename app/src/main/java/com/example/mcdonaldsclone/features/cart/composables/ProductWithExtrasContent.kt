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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.Sauce

@Composable
fun ProductWithExtrasContent(
    innerPadding: PaddingValues,
    productName: String,
    price: Double,
) {
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
                        text = productName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "%.2f zł".format(price),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Replace!
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
                fontWeight = FontWeight.Bold
            )
        }

        items(FakeDataProvider.menuItems.filter { it.subCategoryId == 10L /* 10 == sosy*/ }) { sauce ->
            SauceItem(sauce)
        }
    }
}
@Composable
private fun SauceItem(sauce: MenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle selection */ }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Spice Level Indicator
        if (sauce.spiceLevel!! > 0) {
            Row(Modifier.padding(end = 8.dp)) {
                repeat(sauce.spiceLevel) {
                    Text("🌶", fontSize = 14.sp)
                }
            }
        } else {
            Spacer(modifier = Modifier.width(24.dp)) // align with spicy ones
        }

        // Sauce image
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = sauce.name,
            modifier = Modifier
                .size(40.dp)
                .clip(RectangleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Sauce name
        Text(
            text = sauce.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null
        )
    }
}