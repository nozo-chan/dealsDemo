package com.socialdeal.app.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val deals: List<Deal>
)

@Serializable
data class Deal(
    val unique: String,
    val title: String ,
    val image: String,
    val sold_label: String,
    val company: String,
    val city: String,
    val prices: Prices
)

@Serializable
data class Prices(
    val price: PriceDetail,
    val from_price: PriceDetail?,
    val price_label: String?,
    val discount_label: String?
)

@Serializable
data class PriceDetail(
    val amount: Int,
    val currency: Currency
)

@Serializable
data class Currency(
    val symbol: String,
    val code: String

)
