package com.example.mcdonaldsclone.features.coupons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.composables.BottomBarButton
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.database.model.archiveModel.Sauce
import com.example.mcdonaldsclone.features.cart.composables.ProductWithExtrasContent
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel

@Composable
fun CouponOdbierzScreen(
    couponId: Long,
    onOdbierz: () -> Unit,
    popBack: () -> Unit,
    viewModel: CartViewModel
) {
    val coupon: Coupon = requireNotNull(FakeDataProvider.coupons.find { it.id == couponId })

    var sauceChoosen by remember { mutableStateOf(false) }

    var sauceSelected by remember { mutableStateOf<MenuItem>(FakeDataProvider.menuItems.find { it.id == 202L }!!)}

    var productWithExtras by remember { mutableLongStateOf(108L)}

    var currentlyShowing by remember { mutableStateOf("coupon")}


    if (currentlyShowing == "coupon") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(coupon.imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                IconButton(
                    onClick = { popBack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Wróć",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = coupon.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color.Magenta,
                                    Color(0xFFFFA500)
                                )
                            )
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = coupon.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            }

            coupon.set.listMenuItems.forEach { item ->
                if (sauceChoosen == true && item.id == 108L) {
                    ProductOption(
                        product = item,
                        onClick = {
                            currentlyShowing = "else"
                            productWithExtras == it
                        },
                        showSauce = true,
                        sauceId = sauceSelected.id
                    )
                } else {
                    ProductOption(
                        product = item,
                        onClick = {
                            currentlyShowing = "else"
                            productWithExtras == it
                        }
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.navigationBars.asPaddingValues()),
            ) {
                BottomBarButton(
                    text = "Odbierz",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.addNewItemToSetsInCart(
                            Set(
                                listMenuItems = coupon.set.listMenuItems + sauceSelected,
                                imageResId = coupon.set.imageResId,
                                price = coupon.set.price,
                                quantity = coupon.set.quantity
                            )
                        )
                        onOdbierz()
                    },
                    color = if (!sauceChoosen) Color.Gray else Color(0xFFFFCC00)
                )
            }
        }
    } else {
        ProductWithExtrasContent(
            innerPadding = PaddingValues(),
            menuItemId = 108L,
            onSelectedIndex = { id ->
                sauceChoosen = true
                sauceSelected = FakeDataProvider.menuItems.find { it.id == id }!!
                currentlyShowing = "coupon"
            }
        )
    }

}

@Composable
private fun ProductOption(
    product: MenuItem,
    onClick: (Long) -> Unit,
    showSauce: Boolean = false,
    sauceId: Long = 201L
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
            if (showSauce) {
                Text(
                    text = FakeDataProvider.menuItems.find { it.id == sauceId }!!.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
        if (product.id == 108L) {
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
}