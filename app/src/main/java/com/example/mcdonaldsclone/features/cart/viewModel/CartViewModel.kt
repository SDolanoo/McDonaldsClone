package com.example.mcdonaldsclone.features.cart.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.database.model.archiveModel.CartItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val set: Set,
    private val menuItem: MenuItem,
    private val fakeData: FakeDataProvider,
): ViewModel() {
    val setsInCart = mutableStateListOf<Set>()
    val menuItemsInCart = mutableStateListOf<Pair<MenuItem, Int>>()

    private val _currentCategoryIdViewing = MutableStateFlow<Int>(0)
    val currentCategoryIdViewing: StateFlow<Int> = _currentCategoryIdViewing

    private val _currentProductIdViewing  = MutableStateFlow<Long>(0)
    val currentProductIdViewing: StateFlow<Long> = _currentProductIdViewing

    private val _currentProduct  = MutableStateFlow<MenuItem>(fakeData.menuItems.find { it.id == currentProductIdViewing.value }!!)
    val currentProduct: StateFlow<MenuItem> = _currentProduct

}