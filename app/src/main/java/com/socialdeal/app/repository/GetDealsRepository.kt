package com.socialdeal.app.repository

import com.socialdeal.app.model.WrappedDeals
import com.socialdeal.app.model.DetailResponse

interface GetDealsRepository {
    suspend fun getDeals(): List<WrappedDeals>
    suspend fun fetchDealDetails(): DetailResponse
    suspend fun getFullImageUrl(image:String): String
}