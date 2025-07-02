package com.example.mcdonaldsclone

import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider.menuItems
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import kotlin.collections.first
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

class AddLoyaltyItemToCartUseCase(
    private val cartViewModel: CartViewModel
) {
    fun execute (loyaltyItem: LoyaltyItem) {
        val setToAdd = loyaltyItem.set

        cartViewModel.addNewItemToSetsInCart(setToAdd)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class AddLoyaltyItemToCartUseCaseTest {

    private lateinit var viewModel: CartViewModel
    private lateinit var useCase: AddLoyaltyItemToCartUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val fakeData = FakeDataProvider
        viewModel = CartViewModel(fakeData)
        useCase = AddLoyaltyItemToCartUseCase(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `adds loyalty item set to cart via ViewModel`() = runTest {
        val item = MenuItem(204, "Sos Śmietanowy", R.drawable.smietanowy, 6, 10, 1.90)

        val loyaltyItem = LoyaltyItem(id = 1, title = "Sos śmietanowy", R.drawable.kupon7, points = 250,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 204L }!!
                ),
                imageResId = R.drawable.kupon7,
                price = 00.00,
                quantity = 1,
            )
        )

        useCase.execute(loyaltyItem)

        // then
        val sets = viewModel.setsInCart.value
        assertEquals(1, sets.size)

        val addedSet = sets.first()
        assertTrue(addedSet.listMenuItems.contains(item))
        assertEquals(00.00, addedSet.price, 0.01)
    }
}