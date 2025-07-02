package com.example.mcdonaldsclone

import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider.menuItems
import com.example.mcdonaldsclone.core.database.model.Coupon
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

class AddCouponToCartUseCase(
    private val cartViewModel: CartViewModel
) {
    fun execute(coupon: Coupon, sauceItem: MenuItem) {
        val setToAdd = coupon.set.copy(
            listMenuItems = coupon.set.listMenuItems + sauceItem
        )
        cartViewModel.addNewItemToSetsInCart(setToAdd)
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
class AddCouponToCartUseCaseWithViewModelTest {

    private lateinit var viewModel: CartViewModel
    private lateinit var useCase: AddCouponToCartUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val fakeData = FakeDataProvider
        viewModel = CartViewModel(fakeData)
        useCase = AddCouponToCartUseCase(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `adds coupon set with selected sauce to cart via ViewModel`() = runTest {
        // given
        val item = MenuItem(10, "McChicken", R.drawable.mc_chicken, 2, 2, 19.20, isSetAvailable = true, setPrice = 28.70)

        val sauce =  MenuItem(202, "Ketchup Płatny", R.drawable.ketchup, 6, 10, 1.50)

        val coupon = Coupon(
            1,
            "McChicken + Cheeseburger + małe frytki",
            "Wołowina",
            3490,
            R.drawable.zestaw1,
            false,
            "10:30",
            "05:00",
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 10L }!!,
                    menuItems.find { it.id == 7L }!!,
                    menuItems.find { it.id == 108L }!!
                ),
                imageResId = R.drawable.zestaw1,
                price = 34.90,
                quantity = 1,
            )
        )

        // when
        useCase.execute(coupon, sauce)

        // then
        val sets = viewModel.setsInCart.value
        assertEquals(1, sets.size)

        val addedSet = sets.first()
        assertTrue(addedSet.listMenuItems.contains(item))
        assertTrue(addedSet.listMenuItems.contains(sauce))
        assertEquals(34.90, addedSet.price, 0.01)
    }
}