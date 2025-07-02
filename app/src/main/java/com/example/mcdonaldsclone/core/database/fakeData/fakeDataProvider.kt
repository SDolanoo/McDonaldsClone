package com.example.mcdonaldsclone.core.database.fakeData

import androidx.compose.ui.graphics.Color
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem
import com.example.mcdonaldsclone.core.database.model.LoyaltyPoints
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.archiveModel.Product
import com.example.mcdonaldsclone.core.database.model.Promo
import com.example.mcdonaldsclone.core.database.model.Set
import com.example.mcdonaldsclone.core.database.model.archiveModel.Sauce
import com.example.mcdonaldsclone.core.database.model.SubCategory
import com.example.mcdonaldsclone.core.database.model.UserSettings
import com.example.mcdonaldsclone.features.cart.composables.CategoryDetailsContent
import com.example.mcdonaldsclone.core.database.model.archiveModel.Drinks
import com.example.mcdonaldsclone.core.database.model.archiveModel.ZestawOption
import kotlin.collections.listOf

object FakeDataProvider {

    val subCategory = listOf(
        SubCategory(1, "Wołowina"),
        SubCategory(2, "Kurczak"),
        SubCategory(5, "Wege"),
        SubCategory(3, "Napoje zimne"),
        SubCategory(4, "Woda"),
        SubCategory(6, "McWrap"),
        SubCategory(7, "Sałatki"),
        SubCategory(8, "Kurczaki"),
        SubCategory(9, "Frytki"),
        SubCategory(10, "Sosy"),
        SubCategory(11, "Dressingi")
    )

    val categories = listOf(
        Category(2, "Burgery", R.drawable.burgery),
        Category(3, "Kurczak", R.drawable.kurczaki),
        Category(4, "Napoje", R.drawable.napoje),
        Category(5, "McWrapy i sałatki", R.drawable.wrapy_i_salatki),
        Category(6, "Frytki i dodatki", R.drawable.frytki_i_sosy)
    )

    val menuItems = listOf(

        // === Products ===
        MenuItem(1, "Big Mac", R.drawable.big_mac, 2, 1, 21.30, isSetAvailable = true, setPrice = 30.50),
        MenuItem(2, "McRoyal", R.drawable.mc_royal, 2, 1, 20.60, isSetAvailable = true, setPrice = 30.30),
        MenuItem(3, "McRoyal Podwójny", R.drawable.mc_royal_podw, 2, 1, 26.30, isSetAvailable = true, setPrice = 35.50),
        MenuItem(4, "WieśMac Podwójny", R.drawable.wies_mac_podw, 2, 1, 26.30, isSetAvailable = true, setPrice = 35.50),
        MenuItem(5, "WieśMac", R.drawable.wies_mac, 2, 1, 20.60, isSetAvailable = true, setPrice = 30.30),
        MenuItem(6, "McDouble", R.drawable.mc_double, 2, 1, 9.90),
        MenuItem(7, "Cheeseburger", R.drawable.cheeseburger, 2, 1, 7.50),
        MenuItem(8, "Hamburger", R.drawable.hamburger, 2, 1, 6.90),
        MenuItem(9, "Jalapeno Burger", R.drawable.jalapenoburger, 2, 1, 7.50),
        MenuItem(10, "McChicken", R.drawable.mc_chicken, 2, 2, 19.20, isSetAvailable = true, setPrice = 28.70),
        MenuItem(11, "Chikker", R.drawable.chikker, 2, 2, 7.40),
        MenuItem(12, "Red Chikker", R.drawable.red_chikker, 2, 2, 7.40),
        MenuItem(13, "Veggie Burger", R.drawable.veggie_burger, 2, 5, 20.60, isSetAvailable = true, setPrice = 30.90),
        MenuItem(14, "McWrap Chrupiący Klasyczny", R.drawable.wrap_klasyczny, 5, 6, 22.20, isSetAvailable = true, setPrice = 33.10),
        MenuItem(15, "McWrap Chrupiący Bekon Deluxe", R.drawable.wrap_bekon, 5, 6, 22.70, isSetAvailable = true, setPrice = 33.70),
        MenuItem(16, "Sałatka Kurczak Premium", R.drawable.salatka_premium, 5, 7, 23.90),
        MenuItem(17, "Sałatka", R.drawable.salatka, 5, 7, 9.90),
        MenuItem(18, "9 McNuggets", R.drawable.nuggets9, 3, 8, 21.50),
        MenuItem(19, "Chicken Tenders 5 szt.", R.drawable.tenders5, 3, 8, 23.90),
        MenuItem(20, "6 McNuggets", R.drawable.nuggets6, 3, 8, 19.30, isSetAvailable = true, setPrice = 28.90),
        MenuItem(21, "20 McNuggets", R.drawable.nuggets20, 3, 8, 33.50),
        MenuItem(22, "Chicken Tenders 3 szt.s", R.drawable.tenders3, 3, 8, 18.90, isSetAvailable = true, setPrice = 28.90),

        // === Drinks ===
        MenuItem(101, "Coca-Cola", R.drawable.cola, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(102, "Coca-Cola Zero", R.drawable.cola_zero, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(103, "Sprite", R.drawable.sprite, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(104, "Fanta", R.drawable.fanta, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(105, "Ice Tea", R.drawable.lipton, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(106, "Woda Gazowana", R.drawable.woda_gaz, 4, 4, 8.00, isSizeChangable = false, priceMedium = 8.00, priceLarge = 8.00),
        MenuItem(107, "Woda Niegazowana", R.drawable.woda_niegaz, 4, 4, 8.00, isSizeChangable = false, priceMedium = 8.00, priceLarge = 8.00),
        MenuItem(108, "Frytki", R.drawable.frytki, 6, 9, 9.00, isSizeChangable = true, priceMedium = 9.80, priceLarge = 10.00),

        // === Sauces ===
        MenuItem(201, "Sriracha Mayo", R.drawable.sriracha, 6, 10, 1.90, spiceLevel = 2),
        MenuItem(202, "Ketchup Płatny", R.drawable.ketchup, 6, 10, 1.50),
        MenuItem(203, "Sos Czosnkowy", R.drawable.czosnkowy, 6, 10, 1.90),
        MenuItem(204, "Sos Śmietanowy", R.drawable.smietanowy, 6, 10, 1.90),
        MenuItem(205, "Sos Barbeque", R.drawable.barbeque, 6, 10, 1.90),
        MenuItem(206, "Sos Słodko-Kwaśny", R.drawable.slodko_kwasny, 6, 10, 1.90),
        MenuItem(207, "Sos Vinegret", R.drawable.vinegret, 6, 11, 1.90),
        MenuItem(208, "Sos 1000 Wysp", R.drawable.wysp1000, 6, 11, 1.90),
        MenuItem(209, "Sos Oliwa z Oliwek", R.drawable.oliwa_z_oliwek, 6, 11, 1.90),
        MenuItem(210, "Sos Koperkowy", R.drawable.koperkowy, 6, 11, 1.90),
    )

    val zestawOptions = listOf(
        ZestawOption(1, "Zestaw", 10F),
        ZestawOption(2, "Zestaw Powiększony", 13F)
    )

    val coupons = listOf(
        Coupon(
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
        ),
        Coupon(
            2,
            "McWrap Klasyczny + małe frytki",
            "Kurczak",
            1850,
            R.drawable.zestaw3,
            false,
            "10:30",
            "05:00",
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 14L }!!,
                    menuItems.find { it.id == 108L }!!
                ),
                imageResId = R.drawable.zestaw3,
                price = 18.50,
                quantity = 1,
            )
        ),
        Coupon(
            3,
            "2x (McWrap Klasyczny + małe frytki)",
            "Kurczak",
            3700,
            R.drawable.zestaw2,
            false,
            "10:30",
            "05:00",
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 14L }!!,
                    menuItems.find { it.id == 108L }!!
                ),
                imageResId = R.drawable.zestaw2,
                price = 37.00,
                quantity = 1,
            )
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
        LoyaltyItem(id = 1, title = "Sos śmietanowy", R.drawable.kupon7, points = 250,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 204L }!!
                ),
                imageResId = R.drawable.kupon7,
                price = 00.00,
                quantity = 1,
            )
        ),
        LoyaltyItem(id = 2, title = "Sos słodko-kwaśny", R.drawable.kupon6, points = 250,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 206L }!!
                ),
                imageResId = R.drawable.kupon6,
                price = 00.00,
                quantity = 1,
            )
        ),
        LoyaltyItem(id = 4, title = "Cheeseburger", R.drawable.kupon, points = 1150,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 7L }!!
                ),
                imageResId = R.drawable.kupon,
                price = 00.00,
                quantity = 1,
            )
        ),
        LoyaltyItem(id = 5, title = "McRoyal", R.drawable.kupon2, points = 2950,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 2L }!!
                ),
                imageResId = R.drawable.kupon2,
                price = 00.00,
                quantity = 1,
            )
        ),
        LoyaltyItem(id = 6, title = "Małe frytki", R.drawable.kupon3, points = 750,
            Set(
                listMenuItems = listOf(
                    menuItems.find { it.id == 108L }!!
                ),
                imageResId = R.drawable.kupon3,
                price = 0.00,
                quantity = 1,
            )
        )
    )

    val loyaltyPoints = LoyaltyPoints(currentPoints = 150)

    val userSettings = UserSettings(isDarkMode = true, preferredLanguage = "pl")
}
