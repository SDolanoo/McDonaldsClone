package com.example.mcdonaldsclone.features.cart.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
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
import org.apache.commons.math3.stat.StatUtils.product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryCartScreen(
    goToZamowIOdbierz: () -> Unit,
    viewModel: CartViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val setsInCart by viewModel.setsInCart.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Twoje zamówienie",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { goToZamowIOdbierz() }) {
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
            Row(
                Modifier.fillMaxWidth().padding(WindowInsets.navigationBars.asPaddingValues()),
            ) {
                BottomBarButton(
                    text = "Zamów więcej",
                    modifier = Modifier.weight(1f),
                    onClick = { goToZamowIOdbierz() },
                    color = Color.White
                )
                BottomBarButton(
                    text = "Wybierz miejsce odbioru",
                    modifier = Modifier.weight(1f),
                    onClick = {},
                )

            }

        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 0.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
           contentPadding = PaddingValues(
                bottom = innerPadding.calculateBottomPadding() + 24.dp,
                top = 24.dp
            )
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp) // opcjonalnie, dla estetyki
                ) {
                    Text(
                        text = "PAMIĘTAJ: Raz złożone zamówienie nie może być zmienione, ani anulowane. Upewnij się, że składasz zamówienie we właściwej restauracji. Zamówienie zacznie być przygotowywane od razu po przyjęciu płatności lub, w przypadku zamówień McDrive, w momencie podania kasjerowi numeru zamówienia. Upewnij się, że będziesz w stanie je odebrać w przeciągu 3–5 minut.",
                        fontSize = 10.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        softWrap = true
                    )
                }
            }

            items(setsInCart) { set ->
                val mainItem = set.listMenuItems.firstOrNull()!!
                val extras = set.listMenuItems.drop(1)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = set.imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = mainItem.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        extras.forEach {
                            Text(
                                text = it.name,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "%.2f zł".format(set.price),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedButton(
                                onClick = { viewModel.removeSetFromSetsInCart(set) },
                                shape = CircleShape,
                                contentPadding = PaddingValues(8.dp),
                                modifier = Modifier.size(40.dp),
                                border = BorderStroke(1.dp, Color.Gray),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black,
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Usuń",
                                    modifier = Modifier.size(20.dp) // controls icon size
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            DropdownMenuQuantitySelector(
                                quantity = set.quantity,
                                onQuantityChange = {set.quantity = it}
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = 1.dp)

            }
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

@Composable
fun DropdownMenuQuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text("$quantity")
            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.Black)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            (1..10).forEach {
                DropdownMenuItem(
                    text = { Text("$it") },
                    onClick = {
                        onQuantityChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}