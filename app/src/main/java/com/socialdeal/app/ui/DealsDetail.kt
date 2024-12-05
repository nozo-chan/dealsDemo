package com.socialdeal.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.toUri
import com.socialdeal.app.R
import com.socialdeal.app.helpers.getFormattedAmount
import com.socialdeal.app.helpers.stripHtmlTags
import com.socialdeal.app.ui.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel,
) {
    val dealDetails by viewModel.details.collectAsState()
    val uniqueId = navController.currentBackStackEntry?.arguments?.getString("unique") ?: ""
    val deal = viewModel.deals.collectAsState().value.find { it.data.unique == uniqueId }
        ?: throw IllegalStateException()
    val imageUrl = viewModel.getImageUri(deal.data.image).toUri()
    val currency = deal.data.prices.price.currency.symbol
    val fromPrice = getFormattedAmount(deal.data.prices.from_price?.amount ?: 0)
    val price = getFormattedAmount(deal.data.prices.price.amount)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = deal.data.company
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                val placeholder: Painter = painterResource(R.drawable.ic_launcher_foreground)

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

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = deal.data.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = deal.data.company,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = deal.data.city,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = deal.data.sold_label,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${currency}${fromPrice}",
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    )
                    Text(
                        text = "${currency}${price}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Details:",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Text(
                    text = stripHtmlTags(dealDetails),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}




