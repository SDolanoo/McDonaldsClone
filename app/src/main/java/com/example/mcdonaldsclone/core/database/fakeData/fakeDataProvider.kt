package com.example.mcdonaldsclone.core.database.fakeData

import com.example.mcdonaldsclone.core.database.model.CartItem
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyItem
import com.example.mcdonaldsclone.core.database.model.LoyaltyPoints
import com.example.mcdonaldsclone.core.database.model.Product
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
            "https://example.com/coupon2.jpg",
            false,
            "10:30",
            "05:00"
        ),
        Coupon(
            2,
            "McWrap Klasyczny + małe frytki",
            "Kurczak",
            1850,
            "https://example.com/coupon3.jpg",
            false,
            "10:30",
            "05:00"
        ),
        Coupon(
            3,
            "2x (McWrap Klasyczny + małe frytki)",
            "Kurczak",
            3700,
            "https://example.com/coupon1.jpg",
            false,
            "10:30",
            "05:00"
        ),
    )

    val loyaltyItems = listOf(
        LoyaltyItem(id = 1, title = "Mała kawa gratis", points = 100),
        LoyaltyItem(id = 2, title = "Lody w rożku", points = 150),
        LoyaltyItem(id = 3, title = "Duże frytki", points = 200),
        LoyaltyItem(id = 4, title = "Cheeseburger", points = 250),
        LoyaltyItem(id = 5, title = "McFlurry", points = 300),
        LoyaltyItem(id = 6, title = "Zestaw śniadaniowy", points = 350),
        LoyaltyItem(id = 7, title = "Wrap klasyczny", points = 400),
        LoyaltyItem(id = 8, title = "Big Mac", points = 450),
        LoyaltyItem(id = 9, title = "Zestaw Medium", points = 500),
        LoyaltyItem(id = 10, title = "Zestaw Premium + napój", points = 600)
    )


    val cartItems = listOf(
        CartItem(1, productId = 1, quantity = 2),
        CartItem(2, productId = 4, quantity = 1)
    )

    val loyaltyPoints = LoyaltyPoints(currentPoints = 150)

    val userSettings = UserSettings(isDarkMode = true, preferredLanguage = "pl")
}
