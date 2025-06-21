package com.example.mcdonaldsclone.features.cart.viewModel

import com.example.mcdonaldsclone.core.database.model.CartItem
import com.example.mcdonaldsclone.features.menu.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartItem: CartItem,
    private val product: Product
) {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

//    val totalPrice: StateFlow<Double> = cartItems
//        .map { items -> items.sumOf { it.product.price * it.quantity } }
//        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)


}