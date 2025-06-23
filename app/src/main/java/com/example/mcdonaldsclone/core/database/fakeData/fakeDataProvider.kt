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
        Category(1, "Super Combo"),
        Category(2, "Burgery"),
        Category(3, "Kurczak"),
        Category(4, "Napoje"),
        Category(5, "McWrapy i sałatki"),
        Category(6, "Frytki i dodatki")
    )

    val menuItems = listOf(

        // === Products ===
        MenuItem(1, "Big Mac", R.drawable.kupon, 2, 1, 21.30, isSetAvailable = true, setPrice = 30.50),
        MenuItem(2, "McRoyal", R.drawable.kupon, 2, 1, 20.60, isSetAvailable = true, setPrice = 30.30),
        MenuItem(3, "McRoyal Podwójny", R.drawable.kupon, 2, 1, 26.30, isSetAvailable = true, setPrice = 35.50),
        MenuItem(4, "WieśMac Podwójny", R.drawable.kupon, 2, 1, 26.30, isSetAvailable = true, setPrice = 35.50),
        MenuItem(5, "WieśMac", R.drawable.kupon, 2, 1, 20.60, isSetAvailable = true, setPrice = 30.30),
        MenuItem(6, "McDouble", R.drawable.kupon, 2, 1, 9.90),
        MenuItem(7, "Cheeseburger", R.drawable.kupon, 2, 1, 7.50),
        MenuItem(8, "Hamburger", R.drawable.kupon, 2, 1, 6.90),
        MenuItem(9, "Jalapeno Burger", R.drawable.kupon, 2, 1, 7.50),
        MenuItem(10, "McChicken", R.drawable.kupon, 2, 2, 19.20, isSetAvailable = true, setPrice = 28.70),
        MenuItem(11, "Chikker", R.drawable.kupon, 2, 2, 7.40),
        MenuItem(12, "Red Chikker", R.drawable.kupon, 2, 2, 7.40),
        MenuItem(13, "Veggie Burger", R.drawable.kupon, 2, 5, 20.60, isSetAvailable = true, setPrice = 30.90),
        MenuItem(14, "McWrap Chrupiący Klasyczny", R.drawable.kupon, 5, 6, 22.20, isSetAvailable = true, setPrice = 33.10),
        MenuItem(15, "McWrap Chrupiący Bekon Deluxe", R.drawable.kupon, 5, 6, 22.70, isSetAvailable = true, setPrice = 33.70),
        MenuItem(16, "Sałatka Kurczak Premium", R.drawable.kupon, 5, 7, 23.90),
        MenuItem(17, "Sałatka", R.drawable.kupon, 5, 7, 9.90),
        MenuItem(18, "9 McNuggets", R.drawable.kupon, 3, 8, 21.50),
        MenuItem(19, "Chicken Tenders 5 szt.", R.drawable.kupon, 3, 8, 23.90),
        MenuItem(20, "6 McNuggets", R.drawable.kupon, 3, 8, 19.30, isSetAvailable = true, setPrice = 28.90),
        MenuItem(21, "20 McNuggets", R.drawable.kupon, 3, 8, 33.50),
        MenuItem(22, "Chicken Tenders 3 szt.s", R.drawable.kupon, 3, 8, 18.90, isSetAvailable = true, setPrice = 28.90),

        // === Drinks ===
        MenuItem(101, "Coca-Cola", R.drawable.kupon4, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(102, "Coca-Cola Zero", R.drawable.kupon4, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(103, "Sprite", R.drawable.kupon4, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(104, "Fanta", R.drawable.kupon4, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(105, "Ice Tea", R.drawable.kupon4, 4, 3, 8.10, isSizeChangable = true, priceMedium = 11.10, priceLarge = 12.10),
        MenuItem(106, "Woda Gazowana", R.drawable.kupon4, 4, 4, 8.00, isSizeChangable = false, priceMedium = 8.00, priceLarge = 8.00),
        MenuItem(107, "Woda Niegazowana", R.drawable.kupon4, 4, 4, 8.00, isSizeChangable = false, priceMedium = 8.00, priceLarge = 8.00),
        MenuItem(108, "Frytki", R.drawable.kupon4, 6, 9, 9.00, isSizeChangable = true, priceMedium = 9.80, priceLarge = 10.00),

        // === Sauces ===
        MenuItem(201, "Sriracha Mayo", R.drawable.kupon7, 6, 10, 1.90, spiceLevel = 2),
        MenuItem(202, "Ketchup Płatny", R.drawable.kupon7, 6, 10, 1.50),
        MenuItem(203, "Sos Czosnkowy", R.drawable.kupon7, 6, 10, 1.90),
        MenuItem(204, "Sos Śmietanowy", R.drawable.kupon7, 6, 10, 1.90),
        MenuItem(205, "Sos Barbeque", R.drawable.kupon7, 6, 10, 1.90),
        MenuItem(206, "Sos Słodko-Kwaśny", R.drawable.kupon7, 6, 10, 1.90),
        MenuItem(207, "Sos Vinegret", R.drawable.kupon7, 6, 11, 1.90),
        MenuItem(208, "Sos 1000 Wysp", R.drawable.kupon7, 6, 11, 1.90),
        MenuItem(209, "Sos Oliwa z Oliwek", R.drawable.kupon7, 6, 11, 1.90),
        MenuItem(210, "Sos Koperkowy", R.drawable.kupon7, 6, 11, 1.90),
    )

    val zestawOptions = listOf(
        ZestawOption(1, "Zestaw", 10F),
        ZestawOption(2, "Zestaw Powiększony", 13F)
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
