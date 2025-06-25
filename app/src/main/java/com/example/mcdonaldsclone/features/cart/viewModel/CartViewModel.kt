package com.example.mcdonaldsclone.features.cart.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.database.model.archiveModel.CartItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.Product
import com.example.mcdonaldsclone.core.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val fakeData: FakeDataProvider,
): ViewModel() {
    val setsInCart = mutableStateListOf<Set>()
    val menuItemsInCart = mutableStateListOf<Pair<MenuItem, Int>>()


    private val _currentCategoryIdViewing = MutableStateFlow<Long>(0)
    val currentCategoryIdViewing: StateFlow<Long> = _currentCategoryIdViewing

    private val _currentMenuItemIdViewing  = MutableStateFlow<Long>(1)
    val currentMenuItemIdViewing: StateFlow<Long> = _currentMenuItemIdViewing

    private val _currentMenuItem  = MutableStateFlow<MenuItem>(fakeData.menuItems.find { it.id == currentMenuItemIdViewing.value }!!)
    val currentMenuItem: StateFlow<MenuItem> = _currentMenuItem

    private val _currentSauceItem  = MutableStateFlow<MenuItem>(fakeData.menuItems.firstOrNull { it.subCategoryId == 10L }!!)
    val currentSauceItem: StateFlow<MenuItem> = _currentSauceItem

    private val _composingSet = MutableStateFlow<Set>(Set.EMPTY)
    val composingSet: StateFlow<Set> = _composingSet

    fun setCategory(categoryId: Long) {
        _currentCategoryIdViewing.value = categoryId
    }

    fun setMenuItemId(menuItemId: Long) {
        _currentMenuItemIdViewing.value = menuItemId
        setMenuItem(menuItemId)
    }

    fun setMenuItem(menuItemId: Long) {
        _currentMenuItem.value = fakeData.menuItems.find { it.id == menuItemId }!!
    }

    fun addCurrentMenuItem(quantity: Int) {
        menuItemsInCart.add(Pair(_currentMenuItem.value, quantity))
    }

    fun removeItemFromCart(mI: MenuItem) {
        val itemToRemove = menuItemsInCart.firstOrNull { it.first.id == mI.id }
        itemToRemove?.let {
            menuItemsInCart.remove(it)
        }
    }

    fun addItemToComposingSet(menuItem: MenuItem) {
        val targetSubCategoryId = 10L

        val updatedItems = _composingSet.value.listMenuItems
            .toMutableList()
            .apply {
                if ( menuItem.subCategoryId == targetSubCategoryId ) {
                    val index = indexOfFirst { it.subCategoryId == targetSubCategoryId }
                    if (index != -1) {
                        set(index, menuItem) // Replace existing
                    } else {
                        add(menuItem)
                    }
                } else {
                    add(menuItem)
                }
            }

        _composingSet.value = _composingSet.value.copy(listMenuItems = updatedItems)
    }


    fun replaceComposingSet(newSet: Set) {
        // This makes sure we don't carry over any old items
        _composingSet.value = Set.EMPTY.copy(
            listMenuItems = newSet.listMenuItems,
            imageResId = newSet.imageResId,
            price = newSet.price,
            quantity = newSet.quantity,
        )
    }

    fun addMenuItemToNewSet(menuItem: MenuItem) {
        // If the current set is EMPTY, give it a temp id or name
        val current = _composingSet.value
        val updatedItems = current.listMenuItems + menuItem

        _composingSet.value = current.copy(
            listMenuItems = updatedItems
        )
    }

    fun setCurrentSauce(id: Long) {
        _currentSauceItem.value = FakeDataProvider.menuItems.find { it.id == id }!!
    }
}