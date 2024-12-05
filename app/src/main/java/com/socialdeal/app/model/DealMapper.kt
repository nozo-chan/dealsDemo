package com.socialdeal.app.model

internal fun ApiResponse.toDeals(): List<WrappedDeals> {
    return deals.map { WrappedDeals(it) }
}