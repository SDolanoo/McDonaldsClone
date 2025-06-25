package com.example.mcdonaldsclone.features.cart.composables

import android.R.attr.name
import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.SubCategory
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.features.cart.components.YellowCheck

@Composable
fun ChooseZestaw2ndStepContent(
    innerPadding: PaddingValues,
    onClick: (Long) -> Unit
) {
    var choosenPoduct by remember { mutableStateOf(null) }

    var state by remember { mutableStateOf("category")}
    var currentName by remember { mutableStateOf("Wybierz napój")}
    var currentCategoryViewing by remember { mutableLongStateOf(0L) }

    var selectedIndex by remember { mutableLongStateOf(101L) }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalItemSpacing = 2.dp,
    ) {
        item {
            Text(
                text = currentName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
        }
        item{
            Spacer(Modifier.height(5.dp))
        }
        if (state == "category") {
            item {
                DrinkSubCategory(
                    subCategory = FakeDataProvider.subCategory.find { it.id.toInt() == 3 }!!,
                    imageResId = FakeDataProvider.menuItems.find { it.id.toInt() == 101 }!!.imageResId,
                    onClick = {
                        currentCategoryViewing = it
                        currentName =
                            FakeDataProvider.subCategory.find { it.id.toInt() == 3 }!!.name
                        selectedIndex = 101L
                        state = "subProducts"
                    }
                )
                HorizontalDivider(thickness = 1.dp)
            }
            item {
                DrinkSubCategory(
                    subCategory = FakeDataProvider.subCategory.find { it.id.toInt() == 4 }!!,
                    imageResId = FakeDataProvider.menuItems.find { it.id.toInt() == 106 }!!.imageResId,
                    onClick = {
                        currentCategoryViewing = it
                        currentName =
                            FakeDataProvider.subCategory.find { it.id.toInt() == 4 }!!.name
                        selectedIndex = 106L
                        state = "subProducts"
                    }
                )
                HorizontalDivider(thickness = 1.dp)
            }
        } else {
            items(FakeDataProvider.menuItems.filter { it.subCategoryId == currentCategoryViewing}) { drink ->
                DrinkOptionItem(
                    drink = drink,
                    selected = drink.id == selectedIndex,
                    onClick = {
                        selectedIndex = drink.id
                        onClick(drink.id)
                    }
                )
            }
        }

    }
}

@Composable
private fun DrinkSubCategory(
    subCategory: SubCategory,
    imageResId: Int,
    onClick: (Long) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable { onClick(subCategory.id) },
        leadingContent = {
            Image(
                painter = painterResource(id = imageResId), // Podmień na swoje źródło
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp) // Kwadratowy rozmiar
                    .clip(RoundedCornerShape(8.dp)) // Opcjonalnie – zaokrąglenia
            )
        },
        headlineContent = {
            Text(
                text = subCategory.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        trailingContent = {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "xd")
        }
    )
}

@Composable
private fun DrinkOptionItem (
    drink: MenuItem,
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
        Image(
            painter = painterResource(id = drink.imageResId),
            contentDescription = "title",
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = drink.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }

        if (selected) {
            YellowCheck()
        }
    }
}