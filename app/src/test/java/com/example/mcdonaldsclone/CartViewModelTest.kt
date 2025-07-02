package com.example.mcdonaldsclone

import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.mcdonaldsclone.core.database.model.Set
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private lateinit var fakeDataProvider: FakeDataProvider
    private lateinit var viewModel: CartViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        fakeDataProvider = FakeDataProvider // zakładamy że masz klasę testową
        viewModel = CartViewModel(fakeDataProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun setCategory_updatesCategoryId() = runTest {
        viewModel.setCategory(5L)
        assertEquals(5L, viewModel.currentCategoryIdViewing.value)
    }

    @Test
    fun setMenuItemId_setsCorrectMenuItem() = runTest {
        val id = fakeDataProvider.menuItems.first().id
        viewModel.setMenuItemId(id)
        assertEquals(id, viewModel.currentMenuItem.value.id)
    }

    @Test
    fun addCurrentMenuItem_addsItemToCart() = runTest {
        val id = fakeDataProvider.menuItems.first().id
        viewModel.setMenuItemId(id)
        viewModel.addCurrentMenuItem(2)

        val result = viewModel.menuItemsInCart.first()
        assertEquals(id, result.first.id)
        assertEquals(2, result.second)
    }

    @Test
    fun removeItemFromCart_removesItemCorrectly() = runTest {
        val item = fakeDataProvider.menuItems.first()
        viewModel.setMenuItemId(item.id)
        viewModel.addCurrentMenuItem(1)
        viewModel.removeItemFromCart(item)

        assertTrue(viewModel.menuItemsInCart.isEmpty())
    }

    @Test
    fun addItemToComposingSet_replacesSauceItem() = runTest {
        val sauce1 = fakeDataProvider.menuItems.first { it.subCategoryId == 10L }
        val sauce2 = fakeDataProvider.menuItems.last { it.subCategoryId == 10L }

        viewModel.addItemToComposingSet(sauce1)
        viewModel.addItemToComposingSet(sauce2)

        val composing = viewModel.composingSet.value
        val found = composing.listMenuItems.count { it.subCategoryId == 10L }
        assertEquals(1, found) // Tylko jedna z kategorią 10L powinna być
        assertEquals(sauce2.id, composing.listMenuItems.find { it.subCategoryId == 10L }?.id)
    }

    @Test
    fun addNewItemToSetsInCart_addsCorrectly() = runTest {
        val newSet = Set.EMPTY.copy(quantity = 1)
        viewModel.addNewItemToSetsInCart(newSet)
        assertTrue(viewModel.setsInCart.value.contains(newSet))
    }

    @Test
    fun removeSetFromSetsInCart_removesCorrectly() = runTest {
        val newSet = Set.EMPTY.copy(quantity = 1)
        viewModel.addNewItemToSetsInCart(newSet)
        viewModel.removeSetFromSetsInCart(newSet)

        assertFalse(viewModel.setsInCart.value.contains(newSet))
    }

    @Test
    fun replaceComposingSet_setsCorrectValue() = runTest {
        val set = Set.EMPTY.copy(quantity = 3)
        viewModel.replaceComposingSet(set)
        assertEquals(3, viewModel.composingSet.value.quantity)
    }

    @Test
    fun addComposingSetToSets_addsCurrentComposingSet() = runTest {
        val testItem = fakeDataProvider.menuItems.first()
        viewModel.addItemToComposingSet(testItem)
        viewModel.addComposingSetToSets()

        val sets = viewModel.setsInCart.value
        assertEquals(1, sets.size)
        assertEquals(testItem.id, sets.first().listMenuItems.first().id)
    }

    @Test
    fun setCurrentSauce_setsSauceItemCorrectly() = runTest {
        val sauceItem = fakeDataProvider.menuItems.first { it.subCategoryId == 10L }
        viewModel.setCurrentSauce(sauceItem.id)
        assertEquals(sauceItem.id, viewModel.currentSauceItem.value.id)
    }

    @Test
    fun addMenuItemToComposingSet_addsItemProperly() = runTest {
        val item = fakeDataProvider.menuItems.first()
        viewModel.addMenuItemToComposingSet(item)

        val composing = viewModel.composingSet.value
        assertTrue(composing.listMenuItems.contains(item))
    }
}
