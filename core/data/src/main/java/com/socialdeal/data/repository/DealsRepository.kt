package com.socialdeal.data.repository


import com.socialdeal.network.model.DetailResponse
import com.socialdeal.network.model.WrappedDeal
import kotlinx.coroutines.flow.StateFlow

interface DealsRepository {
    val deals: StateFlow<List<WrappedDeal>>
    val favorites: StateFlow<List<WrappedDeal>>

   suspend fun fetchDealDetails(): DetailResponse
    fun toggleFavorite(unique:String)
    fun getImage(imagePath: String):String
    fun toggleCurrency(dollar:Boolean)
}