package com.socialdeal.app.model

internal fun ApiResponse.toDeals(): List<WrappedDeal> {
    return deals.map { WrappedDeal(it) }
}