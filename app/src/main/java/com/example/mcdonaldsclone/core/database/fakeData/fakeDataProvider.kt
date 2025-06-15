package com.example.mcdonaldsclone.core.database.fakeData

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.model.CartItem
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem
import com.example.mcdonaldsclone.core.database.model.LoyaltyPoints
import com.example.mcdonaldsclone.core.database.model.Product
import com.example.mcdonaldsclone.core.database.model.Promo
import com.example.mcdonaldsclone.core.database.model.UserSettings
import kotlin.collections.listOf

object FakeDataProvider {

    val categories = listOf(
        Category(1, "Zestawy"),
        Category(2, "Burgery"),
        Category(3, "Napoje"),
        Category(4, "Desery")
    )

    val products = listOf(
        Product(
            1,
            "Big Mac",
            "Klasyczny burger z wołowiną",
            "https://example.com/bigmac.jpg",
            18.99,
            2
        ),
        Product(
            2,
            "McZestaw",
            "Zestaw z Big Mac, frytki i napój",
            "https://example.com/zestaw.jpg",
            29.99,
            1
        ),
        Product(
            3,
            "McFlurry Oreo",
            "Deser lodowy z Oreo",
            "https://example.com/mcflurry.jpg",
            9.99,
            4
        ),
        Product(4, "Coca-Cola", "Napój gazowany 0.5L", "https://example.com/coke.jpg", 6.50, 3),
        Product(
            5,
            "Cheeseburger",
            "Burger z serem",
            "https://example.com/cheeseburger.jpg",
            7.99,
            2
        )
    )

    val coupons = listOf(
        Coupon(
            1,
            "McZestaw Powiększony WieśMac + McChicken",
            "Wołowina",
            3490,
            R.drawable.zestaw1,
            false,
            "10:30",
            "05:00"
        ),
        Coupon(
            2,
            "McWrap Klasyczny + małe frytki",
            "Kurczak",
            1850,
            R.drawable.zestaw2,
            false,
            "10:30",
            "05:00"
        ),
        Coupon(
            3,
            "2x (McWrap Klasyczny + małe frytki)",
            "Kurczak",
            3700,
            R.drawable.zestaw3,
            false,
            "10:30",
            "05:00"
        ),
    )

    val promos = listOf(
        Promo(1,
            "Nowe McFlurry Pistacjowe.",
            "Spróbuj i poczuj, o co to zamieszanie!",
            R.drawable.promo4,
            Color(0xFF000000),
            Color(0xFFb0780a)),
        Promo(2,
            "Kiedy masz smaka na Maka...",
            "...zamów McRoyal Cheesy Jalapeno Bacon z serowym sosem Smoky Cheddar i papryczkamy jalapeno!",
            R.drawable.promo3,
            Color(0xFFFFFFFF),
            Color(0xFF7A694A)
        ),
        Promo(3,
            "Odkryj nową Kokosową Iced Latte!",
            "Spróbuj też z bitą śmietanką",
            R.drawable.promo2,
            Color(0xFF000000),
            Color(0xFFD0B783)
        ),
        Promo(4,
            "Dodatkową superopcją w Super Combo!",
            "McNuggets polecają się do Twojego superzestawu!",
            R.drawable.promo,
            Color(0xFFFFFFFF),
            Color(0xFF317332)
        ),
    )

    val loyaltyItems = listOf(
        LoyaltyItem(id = 1, title = "Sos śmietanowy", R.drawable.kupon7, points = 250),
        LoyaltyItem(id = 2, title = "Sos słodko-kwaśny", R.drawable.kupon6, points = 250),
        LoyaltyItem(id = 3, title = "McPops orzechowy", R.drawable.kupon5,  points = 500),
        LoyaltyItem(id = 4, title = "Cheeseburger", R.drawable.kupon, points = 1150),
        LoyaltyItem(id = 5, title = "McRoyal", R.drawable.kupon2, points = 2950),
        LoyaltyItem(id = 6, title = "Małe frytki", R.drawable.kupon3, points = 750),
        LoyaltyItem(id = 7, title = "Mały napój", R.drawable.kupon4, points = 700)
    )


    val cartItems = listOf(
        CartItem(1, productId = 1, quantity = 2),
        CartItem(2, productId = 4, quantity = 1)
    )

    val loyaltyPoints = LoyaltyPoints(currentPoints = 150)

    val userSettings = UserSettings(isDarkMode = true, preferredLanguage = "pl")
}
