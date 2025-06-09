package com.example.mcdonaldsclone.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
//            HomeScreen(
//                onNavigateToCoupons = { navController.navigate(Screen.Coupons.route) },
//                // ...
//            )
        }

        composable(Screen.Coupons.route) {
//            CouponsScreen(onCouponClick = { couponId ->
//                navController.navigate(Screen.CouponDetails.createRoute(couponId))
//            })
        }

        composable(
            route = Screen.CouponDetails.route,
            arguments = listOf(navArgument("couponId") { type = NavType.StringType })
        ) { backStackEntry ->
            val couponId = backStackEntry.arguments?.getString("couponId") ?: return@composable
//            CouponDetailsScreen(couponId = couponId)
        }

        // Analogicznie dla menu, cart, loyalty itd.
    }
}
