package com.socialdeal.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socialdeal.app.ui.Route.ROUTE_FAVORITES
import com.socialdeal.app.ui.viewmodel.DealsViewModel

object Route {
    const val ROUTE_FAVORITES = "favoriteScreen"
}

@Composable
    fun AppNavigation(
    dealsViewModelFactory: DealsViewModel
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "cardScreen") {
            composable("cardScreen") {
                DealsScreen(
                    favorites = {navController.navigate(ROUTE_FAVORITES)},
                    navController = navController,
                    dealsViewModelFactory)
            }

            composable("details/{imageUrl}/{title}/{description}/{city}/{sold}/{price}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val description = backStackEntry.arguments?.getString("description") ?: ""
                val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                val city = backStackEntry.arguments?.getString("city") ?: ""
                val sold = backStackEntry.arguments?.getString("sold") ?: ""
                val fromPrice = backStackEntry.arguments?.getString("fromPrice") ?: ""
                val currency = backStackEntry.arguments?.getString("currency") ?: ""
                val price = backStackEntry.arguments?.getString("price") ?: ""

                DetailsScreen(
                    imageUrl = imageUrl ,
                    title = title,
                    company = description,
                    city = city,
                    sold = sold,
                    price = price,
                    currency=currency,
                    fromPrice= fromPrice,
                    onBackClick = {navController.popBackStack()},
                    viewModel = dealsViewModelFactory)
            }

            composable(ROUTE_FAVORITES) { backStackEntry ->
                FavoriteDeals(navController, dealsViewModelFactory)
            }


        }
    }
