package com.example.mcdonaldsclone.features.cart.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.features.cart.composables.CategoryDetailsContent
import com.example.mcdonaldsclone.features.cart.composables.MainContent
import com.example.mcdonaldsclone.features.cart.composables.ProductDetailsContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamowIOdbierzScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var currentlyViewing by remember { mutableStateOf("main") }
    var currentlyViewingTitle by remember { mutableStateOf("Zamów") }

    var currentCategoryIdViewing by remember { mutableLongStateOf(0) }
    var currentProductIdViewing by remember { mutableLongStateOf(1) }
    var currentProduct by remember { mutableStateOf<MenuItem>(FakeDataProvider.menuItems.find { it.id == currentProductIdViewing }!!) }

    LaunchedEffect(currentProductIdViewing) {
        currentProduct = FakeDataProvider.menuItems.find { it.id == currentProductIdViewing }!!
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        currentlyViewingTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {currentlyViewing = "main"}) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Wróć",
                            tint = Color.Black
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            if (currentlyViewing == "ProductDetails") {
                if (currentProduct.isSetAvailable == true) {
                    Row(
                        Modifier
                            .fillMaxWidth(),
                    ) {
                        OutlinedButton(
                            onClick = { /* Dodaj do zamówienia */ },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp), // kwadratowy feeling,
                            shape = RectangleShape
                        ) {
                            Text("Dodaj do zamówienia")
                        }
                        OutlinedButton(
                            onClick = { /* Skomponuj zestaw */ },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            colors =ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC00)),
                            shape = RectangleShape
                        ) {
                            Text("Skomponuj zestaw")
                        }
                    }
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth(),
                    ) {
                        Button(
                            onClick = { /* Add to order */ }, // TODO dodać do viewModel coś w stylu current  quantity
                            modifier = Modifier.weight(1f).height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC00)),
                            shape = RectangleShape
                            ) {
                            Text("Dodaj do zamówienia")
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        if (currentlyViewing == "main") {
            currentlyViewingTitle = "Zamów"
            MainContent(
                innerPadding = innerPadding,
                moveToCategoryDetails = { categoryId ->
                    currentCategoryIdViewing = categoryId
                    currentlyViewing = "CategoryDetails"}
            )
        }

        if (currentlyViewing == "CategoryDetails") {
            currentlyViewingTitle = FakeDataProvider.categories.find { it.id == currentCategoryIdViewing }!!.name
            CategoryDetailsContent(
                innerPadding = innerPadding,
                categoryId = currentCategoryIdViewing,
                moveToProductContent = {productId ->
                    currentProductIdViewing = productId
                    currentlyViewing = "ProductDetails"
                }
            )
        }

        if (currentlyViewing == "ProductDetails") {
            currentlyViewingTitle = ""
            ProductDetailsContent(
                innerPadding = innerPadding,
                productId = currentProductIdViewing,
            )
        }
    }
}
