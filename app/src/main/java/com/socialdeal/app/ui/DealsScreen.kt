package com.socialdeal.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.socialdeal.app.R
import com.socialdeal.app.helpers.getFormattedAmount
import com.socialdeal.app.ui.viewmodel.DealsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsScreen(
    favorites: () -> Unit,
    navController: NavController,
    dealsViewModel: DealsViewModel = viewModel()
) {
    val dealsList by dealsViewModel.deals.collectAsState()
    val maxAmountOfDeals = dealsList.take(20)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Social Deal") },
                actions = {
                    IconButton(
                        onClick = { favorites() })
                    {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "favoriteList"
                        )
                    }
                    IconButton(
                        onClick = { dealsViewModel.toggleCurrency() })
                    {
                        Icon(
                            imageVector = Icons.Default.Euro,
                            contentDescription = "Settings"
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
            if (maxAmountOfDeals.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(maxAmountOfDeals) { deal ->
                            val priceFrom = deal.data.prices.from_price?.amount ?: 0
                            CustomCard(
                                imageUrl = dealsViewModel.getImageUri(deal.data.image),
                                title = deal.data.title,
                                description = deal.data.company,
                                fromPrice = getFormattedAmount(priceFrom),
                                currency = deal.data.prices.price.currency.code,
                                price = getFormattedAmount(deal.data.prices.price.amount),
                                sold = deal.data.sold_label,
                                city = deal.data.city,
                                favorite = deal.favored,
                                onFavoriteToggle = { dealsViewModel.toggleFavorite(deal.data.unique) },
                                navController = navController
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
}

@Composable
fun CustomCard(
    imageUrl: String,
    title: String,
    description: String,
    fromPrice: String,
    currency: String,
    price: String,
    sold: String,
    city: String,
    favorite: Boolean,
    onFavoriteToggle: () -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Hard coded unique? does not match details unique?
                navController.navigate("details/x6ji36jvyi4mj9fk")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val placeholder: Painter = painterResource(R.drawable.ic_launcher_foreground)

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Image from URL",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = placeholder,
                        error = placeholder
                    )
                    IconToggleButton(
                        checked = favorite,
                        onCheckedChange = { onFavoriteToggle() },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (favorite) "Unfavorite" else "Favorite",
                            tint = if (favorite) Color.Red else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = description,
                        style = TextStyle(
                            fontSize = 14.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = city,
                        style = TextStyle(
                            fontSize = 14.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Sold: $sold",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Cyan
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${currency}${fromPrice}",
                                style = MaterialTheme.typography.bodyMedium,
                                textDecoration = TextDecoration.LineThrough,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${currency}${price}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Green,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}




