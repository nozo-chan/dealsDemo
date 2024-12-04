package com.socialdeal.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socialdeal.app.ui.Route.ROUTE_Favorites
import com.socialdeal.app.ui.viewmodel.DealsViewModel

object Route {
    const val ROUTE_Favorites = "favoriteScreen"
}

@Composable
    fun AppNavigation(
    dealsViewModelFactory: DealsViewModel
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "cardScreen") {
            composable("cardScreen") {
                DealsScreen(
                    favorites = {navController.navigate(ROUTE_Favorites)},
                    navController = navController,
                    dealsViewModelFactory)
            }

            composable("details/{imageUrl}/{title}/{description}/{city}/{sold}/{price}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val description = backStackEntry.arguments?.getString("description") ?: ""
                val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                val city = backStackEntry.arguments?.getString("city") ?: ""
                val sold = backStackEntry.arguments?.getString("sold") ?: ""
                val price = backStackEntry.arguments?.getInt("price") ?: 0

                DetailsScreen(
                    imageUrl = imageUrl ,
                    title = title,
                    description = description,
                    city = city,
                    sold = sold,
                    price = price,
                    onBackClick = {navController.popBackStack()},
                    viewModel = dealsViewModelFactory)
            }

            composable(ROUTE_Favorites) { backStackEntry ->
                FavoriteDeals(navController, dealsViewModelFactory)
            }
        }
    }
