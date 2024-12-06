package com.socialdeal.data.mapper

import com.socialdeal.network.model.ApiResponse
import com.socialdeal.network.model.WrappedDeal

internal fun ApiResponse.toDeals(): List<WrappedDeal> {
    return deals.map { WrappedDeal(it) }
}