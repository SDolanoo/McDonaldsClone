package com.example.mcdonaldsclone.features.cart.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.features.cart.composables.CategoryDetailsContent
import com.example.mcdonaldsclone.features.cart.composables.MainContent
import com.example.mcdonaldsclone.features.cart.composables.ProductDetailsContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamowIOdbierzScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var currentlyViewing by remember { mutableStateOf("main") }

    var currentCategoryIdViewing by remember { mutableLongStateOf(0) }
    var currentProductIdViewing by remember { mutableLongStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Zeskanuj kod, aby zbierać punkty",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Wróć",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Text(
                        text = "${FakeDataProvider.loyaltyPoints.currentPoints} pkt",
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = scrollBehavior,



            )
        },
        bottomBar = {
            if (currentlyViewing == "ProductDetails") {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { /* Add to order */ }, // TODO dodać do viewModel coś w stylu current  quantity
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Dodaj do zamówienia")
                    }
                    Button(
                        onClick = { /* Compose set */ }, // TODO do viewModel zaciąganie i pobieranie informacji
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC00)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Skomponuj zestaw") // są produkty któe mogą mieć skomponuj zestaw, ale niektóre nie
                    }
                }
            }
        }
    ) { innerPadding ->
        if (currentlyViewing == "main") {
            MainContent(
                innerPadding = innerPadding,
                moveToCategoryDetails = { categoryId ->
                    currentCategoryIdViewing = categoryId
                    currentlyViewing = "CategoryDetails"}
            )
        }

        if (currentlyViewing == "CategoryDetails") {
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
            ProductDetailsContent(
                innerPadding = innerPadding,
                productId = currentProductIdViewing,
            )
        }
    }
}
