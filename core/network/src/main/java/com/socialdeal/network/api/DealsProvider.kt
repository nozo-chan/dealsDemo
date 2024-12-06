package com.socialdeal.network.api

import com.socialdeal.network.model.ApiResponse
import com.socialdeal.network.model.DetailResponse


interface DealsProvider {
    suspend fun fetchDeals(): ApiResponse
    suspend fun fetchDealDetails(): DetailResponse
}