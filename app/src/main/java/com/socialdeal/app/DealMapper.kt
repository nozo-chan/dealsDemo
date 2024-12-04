package com.socialdeal.app

import com.socialdeal.app.model.WrappedDeals
import com.socialdeal.app.model.ApiResponse

internal fun ApiResponse.toDeals(): List<WrappedDeals> {
    return deals.map { WrappedDeals(it) }
}