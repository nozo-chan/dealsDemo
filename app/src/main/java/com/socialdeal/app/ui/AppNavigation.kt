package com.socialdeal.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socialdeal.app.ui.Route.ROUTE_FAVORITES
import com.socialdeal.app.ui.viewmodel.DealsViewModel
import com.socialdeal.app.ui.viewmodel.DetailViewModel

object Route {
    const val ROUTE_FAVORITES = "favoriteScreen"
}

@Composable
    fun AppNavigation(
    dealsViewModelFactory: DealsViewModel,
    detailsViewModel: DetailViewModel
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "cardScreen") {
            composable("cardScreen") {
                DealsScreen(
                    favorites = {navController.navigate(ROUTE_FAVORITES)},
                    navController = navController,
                    dealsViewModelFactory)
            }

            composable("details/{unique}") { backStackEntry ->
                DetailsScreen(
                    navController = navController,
                    onBackClick = {navController.popBackStack()},
                    viewModel = detailsViewModel)
            }

            composable(ROUTE_FAVORITES) { backStackEntry ->
                FavoriteDeals(navController, dealsViewModelFactory)
            }
        }
    }
