package com.socialdeal.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.socialdeal.app.helpers.getFormattedAmount
import com.socialdeal.app.ui.viewmodel.DealsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDeals(navController: NavController, dealsViewModel: DealsViewModel = viewModel()) {
    val favorites = dealsViewModel.getFavorites().collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorites") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(favorites) { deals ->
                        val priceFrom = deals.data.prices.from_price?.amount ?: 0
                            CustomCard(
                                imageUrl = dealsViewModel.getImageUri(deals.data.image),
                                title = deals.data.title,
                                description = deals.data.company,
                                fromPrice = getFormattedAmount(priceFrom),
                                currency = deals.data.prices.price.currency.code,
                                price = getFormattedAmount(deals.data.prices.price.amount),
                                sold = deals.data.sold_label,
                                city = deals.data.city,
                                favorite = deals.favored,
                                onFavoriteToggle = { dealsViewModel.toggleFavorite(deals.data.unique) },
                                navController= navController
                            )
                        }
                    item {
                        Text(
                            text = "End of the List",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }