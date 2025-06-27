package com.example.mcdonaldsclone.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Coupons : Screen("coupons")
    object CouponDetails : Screen("coupons/{couponId}") {
        fun createRoute(couponId: Long) = "coupons/$couponId"
    }
    object CouponOdbierz : Screen("coupons/{couponId}/odbierz") {
        fun createRoute(couponId: Long) = "coupons/$couponId/odbierz"
    }

    object Menu : Screen("menu")
    object ProductDetails : Screen("menu/{productId}") {
        fun createRoute(productId: String) = "menu/$productId"
    }

    object ZamowIOdbierz : Screen("zamowIOdbierz")

    object SummaryCart: Screen("summaryCart")

    object Loyalty : Screen("loyalty")
    object LoyaltyDetails : Screen("loyalty/{loyaltyItemId}") {
        fun createRoute(loyaltyItemId: Long) = "loyalty/$loyaltyItemId"
    }
    object Settings : Screen("settings")
    object MojeM : Screen("mojeM")
    object QR : Screen("QR")
}
