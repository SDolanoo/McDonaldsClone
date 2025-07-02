package com.example.mcdonaldsclone.features.cart.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.composables.BottomBarButton
import com.example.mcdonaldsclone.core.composables.CategoryDetailsContent
import com.example.mcdonaldsclone.core.composables.ChooseZestaw1stStepContent
import com.example.mcdonaldsclone.core.composables.ChooseZestaw2ndStepContent
import com.example.mcdonaldsclone.core.composables.ChooseZestawContent
import com.example.mcdonaldsclone.core.composables.ChooseZestawFinalStepContent
import com.example.mcdonaldsclone.core.composables.MainContent
import com.example.mcdonaldsclone.core.composables.ProductDetailsContent
import com.example.mcdonaldsclone.core.composables.ProductWithExtrasContent
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel
import com.example.mcdonaldsclone.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamowIOdbierzScreen(
    onNavigateToSymmaryCart: () -> Unit,
    goBack: () -> Unit,
    viewModel: CartViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val setsInCart by viewModel.setsInCart.collectAsState()

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
                    IconButton(onClick = {if (currentlyViewing == "main") goBack() else currentlyViewing = "main"}) {
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
            if  (currentlyViewing == "main" && setsInCart.isNotEmpty()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues())
                ) {
                    CartBottomBar(itemCount = setsInCart.size, onClick = {onNavigateToSymmaryCart()})
                }
            }
            if (currentlyViewing == "ProductDetails") {
                if (currentProduct.subCategoryId == 7L || currentProduct.subCategoryId == 9L) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(WindowInsets.navigationBars.asPaddingValues())
                    ) {
                        BottomBarButton(
                            text = "Dodaj do zamówienia",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.addNewItemToSetsInCart(
                                    Set(
                                        listMenuItems = listOf<MenuItem>(currentProduct, currentSauce),
                                        imageResId = currentProduct.imageResId,
                                        price = currentProduct.basePrice + currentSauce.basePrice,
                                        quantity = 1
                                    )
                                )
                                currentlyViewing = "main"
                            },
                        )
                    }
                } else {
                    if (currentProduct.isSetAvailable == true) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(WindowInsets.navigationBars.asPaddingValues()),
                        ) {
                            BottomBarButton(
                                text = "Dodaj do zamówienia",
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    viewModel.addNewItemToSetsInCart(
                                        Set(
                                            listMenuItems = listOf<MenuItem>(currentProduct),
                                            imageResId = currentProduct.imageResId,
                                            price = currentProduct.basePrice,
                                            quantity = quantity
                                        )
                                    )
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
                            Modifier
                                .fillMaxWidth()
                                .padding(WindowInsets.navigationBars.asPaddingValues())
                        ) {
                            BottomBarButton(
                                text = "Dodaj do zamówienia",
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    viewModel.addNewItemToSetsInCart(
                                        Set(
                                            listMenuItems = listOf<MenuItem>(currentProduct),
                                            imageResId = currentProduct.imageResId,
                                            price = currentProduct.basePrice,
                                            quantity = quantity
                                        )
                                    )
                                    currentlyViewing = "main"
                                },
                            )
                        }
                    }
                }
            }
            if (currentlyViewing == "ChooseZestawContent") {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues()),
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
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues()),
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
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues()),
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
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues()),
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
                    Modifier
                        .fillMaxWidth()
                        .padding(WindowInsets.navigationBars.asPaddingValues()),
                ) {
                    BottomBarButton(
                        text = "Dodaj do zamówienia",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            viewModel.addComposingSetToSets()
                            currentlyViewing = "main"
                        }
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
            if (currentProduct.subCategoryId == 7L || currentProduct.subCategoryId == 9L) {
                ProductWithExtrasContent(
                    innerPadding = innerPadding,
                    menuItemId = currentMenuItemIdViewing,
                    onSelectedIndex = {viewModel.setCurrentSauce(it)}
                )
            } else {
                ProductDetailsContent(
                    innerPadding = innerPadding,
                    menuItemId = currentMenuItemIdViewing,
                    onQuantity = { quantity = it }
                )
            }
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

@Composable
fun CartBottomBar(
    itemCount: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp) // Zmniejszony o 1/5 z 80dp
            .background(Color(0xFFFFCC00))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart // tekst po lewej
    ) {
        Text(
            text = "Przejdź do podsumowania",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        // Ikona torby z badge'em
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-12).dp, y = (-36).dp) // bardziej wystająca
                .zIndex(1f)
        ) {
            // Większa ikona torby
            Image(
                painter = painterResource(id = R.drawable.cart_icon),
                contentDescription = "Cart Icon",
                modifier = Modifier.size(72.dp).scale(1.2f).offset(y = 19.dp), // większa ikona
                contentScale = ContentScale.Fit
            )

            // Badge
            if (itemCount > 0) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .scale(1.1f)
                        .background(Color.White, shape = CircleShape)
                        .align(Alignment.TopStart)
                        .offset(x = 5.dp, y = 21.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = itemCount.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


