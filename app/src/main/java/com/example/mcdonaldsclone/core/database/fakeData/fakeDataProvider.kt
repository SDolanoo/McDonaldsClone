package com.example.mcdonaldsclone.core.database.fakeData

import com.example.mcdonaldsclone.core.database.model.CartItem
import com.example.mcdonaldsclone.core.database.model.Category
import com.example.mcdonaldsclone.core.database.model.Coupon
import com.example.mcdonaldsclone.core.database.model.LoyaltyPoints
import com.example.mcdonaldsclone.core.database.model.Product
import com.example.mcdonaldsclone.core.database.model.UserSettings
import java.time.LocalDate

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
            "2 za 1 Big Mac",
            "Kup jeden Big Mac, drugi gratis!",
            "https://example.com/coupon1.jpg",
            false,
            LocalDate.now().plusDays(5)
        ),
        Coupon(
            2,
            "-20% na McZestaw",
            "Zniżka na dowolny zestaw",
            "https://example.com/coupon2.jpg",
            false,
            LocalDate.now().plusDays(3)
        ),
        Coupon(
            3,
            "Gratis McFlurry",
            "Przy zamówieniu powyżej 30zł",
            "https://example.com/coupon3.jpg",
            true,
            LocalDate.now().minusDays(1)
        ) // expired
    )

    val cartItems = listOf(
        CartItem(1, productId = 1, quantity = 2),
        CartItem(2, productId = 4, quantity = 1)
    )

    val loyaltyPoints = LoyaltyPoints(currentPoints = 150)

    val userSettings = UserSettings(isDarkMode = true, preferredLanguage = "pl")
}
