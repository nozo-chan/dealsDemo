package com.socialdeal.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.socialdeal.app.R
import com.socialdeal.app.ui.viewmodel.DealsViewModel
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDeals(navController: NavController, dealsViewModel: DealsViewModel = viewModel()) {
    val favorites = dealsViewModel.getFavorites().collectAsState().value
    val image by dealsViewModel.imageUrl.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Item Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (favorites.isEmpty() && image.isEmpty()) {
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
                    favorites.forEach { deals ->
                        item {
                            CustomCard(
                                imageUrl = dealsViewModel.getImage(deals.deal.image),
                                title = deals.deal.title,
                                description = deals.deal.company,
                                price = deals.deal.prices.price.amount,
                                sold = deals.deal.sold_label,
                                city = deals.deal.city,
                                favorite = deals.favorites,
                                onFavoriteToggle = { dealsViewModel.toggleFavorites(deals.deal.unique) },
                            )
                        }
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
    description: String ,
    price: Int,
    sold: String,
    city: String,
    favorite: Boolean,
    onFavoriteToggle: () -> Unit, ) {

    val encodedImageUrl = URLEncoder.encode(imageUrl, "UTF-8")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(8.dp),

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val placeholder: Painter = painterResource(R.drawable.ic_launcher_foreground)

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                IconToggleButton(
                    checked = favorite,
                    onCheckedChange = { onFavoriteToggle() }
                ) {
                    Icon(
                        imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (favorite) "Unfavorite" else "Favorite",
                        tint = if (favorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(24.dp) // Adjust the size of the icon here
                    )
                }
            }

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

            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
            )

            Text(
                text = description,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            )

            Text(
                text = city,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            )

            Text(
                text = sold,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            )

            Text(
                text = price.toString(),
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            )

        }
    }
}