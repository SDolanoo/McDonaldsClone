package com.example.mcdonaldsclone.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.mcdonaldsclone.features.QRCode.QRCodeScreen
import com.example.mcdonaldsclone.features.QRCode.QRCodeViewModel
import com.example.mcdonaldsclone.features.cart.view.ZamowIOdbierzScreen
import com.example.mcdonaldsclone.features.cart.viewModel.CartViewModel
import com.example.mcdonaldsclone.features.coupons.CouponDetailsScreen
import com.example.mcdonaldsclone.features.home.HomeScreen
import com.example.mcdonaldsclone.features.loyalty.LoyaltyCardDetailsScreen
import com.example.mcdonaldsclone.features.loyalty.LoyaltyCardsScreen
import com.example.mcdonaldsclone.features.mojeM.MojeMScreen
import com.example.mcdonaldsclone.features.cart.view.SummaryCartScreen
import com.example.mcdonaldsclone.features.coupons.CouponOdbierzScreen
import com.example.mcdonaldsclone.features.makeOrder.MakeOrderScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = "cart_nav_graph",
    ) {
        navigation(
            startDestination = Screen.Home.route,
            route = "cart_nav_graph"
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToCoupons = { navController.navigate(Screen.Loyalty.route) },
                    onNavigateToQR = { navController.navigate(Screen.QR.route) },
                    onNavigateToZamowIOdbierz = { navController.navigate(Screen.ZamowIOdbierz.route) },
                    onNavigateToMojeM = { navController.navigate(Screen.MojeM.route) }
                )
            }

            composable(Screen.ZamowIOdbierz.route) {
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                ZamowIOdbierzScreen(
                    onNavigateToSymmaryCart = {navController.navigate(Screen.SummaryCart.route)},
                    goBack = {navController.navigate(Screen.Home.route)},
                    cartViewModel,

                )
            }


            composable(Screen.SummaryCart.route) {
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                SummaryCartScreen(
                    goToZamowIOdbierz = {navController.navigate(Screen.ZamowIOdbierz.route)},
                    onMakeOrder = { navController.navigate(Screen.MakeOrder.route) },
                    cartViewModel
                )

            }

            composable(Screen.MojeM.route) {
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val qRCodeViewModel: QRCodeViewModel = hiltViewModel(parentEntry)
                MojeMScreen(
                    onNavigateToCoupons = { couponId ->
                        navController.navigate(Screen.CouponDetails.createRoute(couponId))
                    },
                    onNavigateToLoyalty = { navController.navigate(Screen.Loyalty.route) },
                    onNavigateToQR = { navController.navigate(Screen.QR.route) },
                    viewModel = qRCodeViewModel
                )
            }

            composable(Screen.MakeOrder.route) {
                MakeOrderScreen(
                    onBackClick = {navController.navigate(Screen.ZamowIOdbierz.route)}
                )
            }

            composable(Screen.QR.route) {
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val qRCodeViewModel: QRCodeViewModel = hiltViewModel(parentEntry)
                QRCodeScreen(
                    popBack = { navController.popBackStack() },
                    viewModel = qRCodeViewModel
                    )
            }

            composable(
                route = Screen.CouponDetails.route,
                arguments = listOf(navArgument("couponId") { type = NavType.LongType })
            ) { backStackEntry ->
                val couponId = backStackEntry.arguments?.getLong("couponId") ?: return@composable
                CouponDetailsScreen(
                    couponId = couponId,
                    onOdbierz = { couponId ->
                        navController.navigate(Screen.CouponOdbierz.createRoute(couponId))
                    },
                    popBack = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.CouponOdbierz.route,
                arguments = listOf(navArgument("couponId") { type = NavType.LongType })
            ) { backStackEntry ->
                val couponId = backStackEntry.arguments?.getLong("couponId") ?: return@composable
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                CouponOdbierzScreen(
                    couponId = couponId,
                    onOdbierz = {
                        navController.navigate(Screen.SummaryCart.route)
                    },
                    popBack = { navController.popBackStack() },
                    viewModel = cartViewModel
                )
            }

            composable(Screen.Loyalty.route) {
                LoyaltyCardsScreen(
                    onCardClick = { loyaltyItemId ->
                        navController.navigate(Screen.LoyaltyDetails.createRoute(loyaltyItemId))
                    },
                    popBack = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.LoyaltyDetails.route,
                arguments = listOf(navArgument("loyaltyItemId") { type = NavType.LongType })
            ) { backStackEntry ->
                val loyaltyItemId =
                    backStackEntry.arguments?.getLong("loyaltyItemId") ?: return@composable
                val parentEntry = remember { navController.getBackStackEntry("cart_nav_graph") }
                val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                LoyaltyCardDetailsScreen(
                    loyaltyItemId = loyaltyItemId,
                    onOdbierz = {
                        navController.navigate(Screen.SummaryCart.route)
                    },
                    popBack = { navController.popBackStack() },
                    viewModel = cartViewModel
                )
            }
        }
    }
}
