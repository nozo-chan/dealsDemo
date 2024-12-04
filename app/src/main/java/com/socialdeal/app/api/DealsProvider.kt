package com.socialdeal.app.api

import com.socialdeal.app.model.DetailResponse
import com.socialdeal.app.model.ApiResponse

interface DealsProvider {

    suspend fun fetchDeals(): ApiResponse
    suspend fun fetchDealDetails(): DetailResponse
    suspend fun getFullImageUrl(image:String): String
}