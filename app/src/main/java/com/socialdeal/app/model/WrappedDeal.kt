package com.socialdeal.app.model

data class WrappedDeal(
    val data: Deal,
    val favored: Boolean = false
)
