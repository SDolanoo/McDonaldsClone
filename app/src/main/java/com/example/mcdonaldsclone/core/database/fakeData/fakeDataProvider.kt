package com.example.mcdonaldsclone.core.database.fakeData

import androidx.compose.ui.graphics.Color
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem
import com.example.mcdonaldsclone.core.database.model.LoyaltyPoints
import com.example.mcdonaldsclone.features.menu.model.Product
import com.example.mcdonaldsclone.core.database.model.Promo
import com.example.mcdonaldsclone.features.menu.model.Sauce
import com.example.mcdonaldsclone.core.database.model.SubCategory
import com.example.mcdonaldsclone.core.database.model.UserSettings
import com.example.mcdonaldsclone.features.menu.model.ZestawOption
import kotlin.collections.listOf

object FakeDataProvider {

    val subCategory = listOf(
        SubCategory(1, "Wołowina"),
        SubCategory(2, "Kurczak")
    )

    val categories = listOf(
        Category(1, "Super Combo"),
        Category(2, "Burgery"),
        Category(3, "Kurczak")
    )

    val products = listOf(
        Product(
            1,
            "Big Mac",
            "Klasyczny burger z wołowiną",
            "https://example.com/bigmac.jpg",
            18.99,
            2,
            true,
            1
        ),
        Product(
            2,
            "McChicken",
            "Zestaw z Big Mac, frytki i napój",
            "https://example.com/zestaw.jpg",
            29.99,
            2,
            true,
            2
        ),
        Product(
            3,
            "2 x burger albo 4 McNuggets i burger + śr. frytki + śr. napó",
            "Deser lodowy z Oreo",
            "https://example.com/mcflurry.jpg",
            9.99,
            1,
            true,
            null
        ),
        Product(4,
            "9 McNuggets",
            "costam",
            "https://example.com/coke.jpg",
            6.50,
            3,
            true,
            2
        ),
        Product(
            5,
            "20 McNuggets",
            "costam",
            "https://example.com/cheeseburger.jpg",
            7.99,
            3,
            true,
            2
        )
    )

    val zestawOptions = listOf(
        ZestawOption(1, "Zestaw", 10F),
        ZestawOption(2, "Zestaw Powiększony", 13F)
    )

    val sauces = listOf(
        Sauce(1, "Sos Nether Flame", spiceLevel = 3),
        Sauce(2, "Sriracha Mayo", spiceLevel = 2),
        Sauce(3, "Sos Czosnkowy"),
        Sauce(4, "Sos Słodko-Kwaśny"),
        Sauce(5, "Sos Śmietanowy"),
        Sauce(6, "Sos Barbeque")
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

    val loyaltyPoints = LoyaltyPoints(currentPoints = 150)

    val userSettings = UserSettings(isDarkMode = true, preferredLanguage = "pl")
}
