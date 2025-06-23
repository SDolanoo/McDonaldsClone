package com.example.mcdonaldsclone.features.cart.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.Product

@Composable
fun MainContent(
    innerPadding: PaddingValues,
    moveToCategoryDetails: (Long) -> Unit
) {
    val listaKategorii: List<Category> = FakeDataProvider.categories
    val products: List<MenuItem> = FakeDataProvider.menuItems
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalItemSpacing = 2.dp,
    ) {
        listaKategorii.forEach { category ->
            item {
                if (category.id > 1) {
                    HorizontalDivider(thickness = 1.dp)
                }
            }
            item {

                ListItem(
                    modifier = Modifier.clickable {moveToCategoryDetails(category.id.toLong())},
                    leadingContent = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background), // Podmień na swoje źródło
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp) // Kwadratowy rozmiar
                                .clip(RoundedCornerShape(8.dp)) // Opcjonalnie – zaokrąglenia
                        )
                    },
                    headlineContent = {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                )
            }
            item {
                if (category.id.toInt()== listaKategorii.size - 1) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}