package com.socialdeal.app.repository

import com.socialdeal.app.model.WrappedDeal
import com.socialdeal.app.model.DetailResponse
import kotlinx.coroutines.flow.StateFlow

interface DealsRepository {
    val deals: StateFlow<List<WrappedDeal>>
    val favorites: StateFlow<List<WrappedDeal>>

   suspend fun fetchDealDetails(): DetailResponse
    fun toggleFavorite(unique:String)
    fun getImage(imagePath: String):String
    fun toggleCurrency(dollar:Boolean)
}