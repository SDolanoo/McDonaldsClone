package com.example.mcdonaldsclone.features.cart.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.features.cart.components.BottomBarButton
import com.example.mcdonaldsclone.features.cart.composables.CategoryDetailsContent
import com.example.mcdonaldsclone.features.cart.composables.ChooseZestaw1stStepContent
import com.example.mcdonaldsclone.features.cart.composables.ChooseZestaw2ndStepContent
import com.example.mcdonaldsclone.features.cart.composables.ChooseZestawContent
import com.example.mcdonaldsclone.features.cart.composables.ChooseZestawFinalStepContent
import com.example.mcdonaldsclone.features.cart.composables.MainContent
import com.example.mcdonaldsclone.features.cart.composables.ProductDetailsContent
import com.example.mcdonaldsclone.features.cart.composables.ProductWithExtrasContent
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamowIOdbierzScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var currentlyViewing by remember { mutableStateOf("main") }
    var currentlyViewingTitle by remember { mutableStateOf("Zamów") }

    var quantity by remember { mutableIntStateOf(1) }

    val currentCategoryIdViewing by viewModel.currentCategoryIdViewing.collectAsState()
    val currentMenuItemIdViewing by viewModel.currentMenuItemIdViewing.collectAsState()
    val currentProduct by viewModel.currentMenuItem.collectAsState()
    val currentSauce by viewModel.currentSauceItem.collectAsState()

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
                        Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                    ) {
                        BottomBarButton(
                            text = "Dodaj do zamówienia",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.addCurrentMenuItem(quantity)
                                currentlyViewing = "main"
                            },
                            color = Color.White
                        )
                        BottomBarButton(
                            text = "Skomponuj zestaw",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                currentlyViewing = "ChooseZestawContent"
                                      },
                        )
                    }
                } else {
                    Row(
                        Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues())
                    ) {
                        BottomBarButton(
                            text = "Dodaj do zamówienia",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.addCurrentMenuItem(quantity)
                                currentlyViewing = "main"
                            },
                        )
                    }
                }
            }
            if (currentlyViewing == "ChooseZestawContent") {
                Row(
                    Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Dalej: Wybierz dodatek do zestawu",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.replaceComposingSet(Set(
                                listMenuItems = listOf(currentProduct),
                                imageResId = currentProduct.imageResId,
                                price = currentProduct.setPrice!!,
                                quantity = 1
                            ))
                            currentlyViewing = "ChooseZestaw1stStep"
                        },
                    )
                }
            }
            if (currentlyViewing == "ChooseZestaw1stStep") {
                Row(
                    Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Dalej: Wybierz napój",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.addItemToComposingSet(currentProduct)
                            currentlyViewing = "ChooseZestaw2ndStep"
                        }
                    )
                }
            }
            if (currentlyViewing == "ChooseExtrasForSide") {
                Row(
                    Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Potwierdź",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.addItemToComposingSet(currentSauce)
                            currentlyViewing = "ChooseZestaw1stStep"
                        }
                    )
                }
            }
            if (currentlyViewing == "ChooseZestaw2ndStep") {
                Row(
                    Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Dalej: Zobacz swoje zamówienie",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.addItemToComposingSet(currentProduct)
                            currentlyViewing = "ChooseZestawFinalStep"
                        }
                    )
                }
            }
            if (currentlyViewing == "ChooseZestawFinalStep") {
                Row(
                    Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Dodaj do zamówienia",
                        modifier = Modifier.weight(1f),
                        onClick = { currentlyViewing = "main" },
                    )
                }
            }
        }
    ) { innerPadding ->
        if (currentlyViewing == "main") {
            currentlyViewingTitle = "Zamów"
            MainContent(
                innerPadding = innerPadding,
                moveToCategoryDetails = { categoryId ->
                    viewModel.setCategory(categoryId)
                    currentlyViewing = "CategoryDetails"
                }
            )
        }

        if (currentlyViewing == "CategoryDetails") {
            currentlyViewingTitle = FakeDataProvider.categories.find { it.id == currentCategoryIdViewing }!!.name
            CategoryDetailsContent(
                innerPadding = innerPadding,
                categoryId = currentCategoryIdViewing,
                moveToProductContent = { menuItemId ->
                    viewModel.setMenuItemId(menuItemId)
                    currentlyViewing = "ProductDetails"
                }
            )
        }

        if (currentlyViewing == "ProductDetails") {
            currentlyViewingTitle = ""
            ProductDetailsContent(
                innerPadding = innerPadding,
                menuItemId = currentMenuItemIdViewing,
                onQuantity = {quantity = it}
            )
        }
        
        if (currentlyViewing == "ChooseZestawContent") {
            currentlyViewingTitle = ""
            ChooseZestawContent(
                innerPadding = innerPadding,
                menuItemId = currentMenuItemIdViewing,
                )

        }

        if(currentlyViewing == "ChooseZestaw1stStep") {
            currentlyViewingTitle = ""
            ChooseZestaw1stStepContent(
                innerPadding = innerPadding,
                navigateToProductWithExtrasContent = { menuItemId ->
                    viewModel.setMenuItemId(menuItemId)
                    currentlyViewing = "ChooseExtrasForSide"
                }
            )

        }

        if (currentlyViewing == "ChooseExtrasForSide") {
            currentlyViewingTitle = ""
            ProductWithExtrasContent(
                innerPadding = innerPadding,
                menuItemId = currentMenuItemIdViewing,
                onSelectedIndex = {viewModel.setCurrentSauce(it)}
            )
        }

        if(currentlyViewing == "ChooseZestaw2ndStep") {
            currentlyViewingTitle = ""
            ChooseZestaw2ndStepContent (
                innerPadding = innerPadding,
                onClick = {viewModel.setMenuItemId(it)}
            )
        }

        if(currentlyViewing == "ChooseZestawFinalStep") {
            currentlyViewingTitle = ""
            ChooseZestawFinalStepContent(
                innerPadding = innerPadding,
                menuItemId = currentMenuItemIdViewing,
                viewModel = viewModel
            )
        }
    }
}
