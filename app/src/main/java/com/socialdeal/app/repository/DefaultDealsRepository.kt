package com.socialdeal.app.repository

import com.socialdeal.app.api.DealsProvider
import com.socialdeal.app.model.APIConstants
import com.socialdeal.app.model.WrappedDeals
import com.socialdeal.app.model.DetailResponse
import com.socialdeal.app.model.toDeals



class DefaultDealsRepository(private val apiProvider: DealsProvider): GetDealsRepository {

    override suspend fun getDeals(): List<WrappedDeals> {
        val response = apiProvider.fetchDeals()
         return response.toDeals()
     }

    override suspend fun getFullImageUrl(image:String): String {
        return apiProvider.getFullImageUrl(image)
    }

    override suspend fun fetchDealDetails(): DetailResponse {
        return apiProvider.fetchDealDetails()
    }

    override fun getImage(image: String):String {
        return APIConstants.IMAGE_PREFIX + image
    }
}