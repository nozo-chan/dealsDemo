package com.socialdeal.app.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    val unique: String,
    val title: String,
    val company: String,
    val description: String,
    val city: String,
    val sold_label: String,
    val prices: Prices
)
