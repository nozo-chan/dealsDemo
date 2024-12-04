package com.socialdeal.app.model

data class WrappedDeals(
    val deal: Deal,
    val favorites: Boolean = false
)
