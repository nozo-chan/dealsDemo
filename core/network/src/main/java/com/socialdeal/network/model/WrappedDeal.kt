package com.socialdeal.network.model

data class WrappedDeal(
    val data: Deal,
    val favored: Boolean = false
)
