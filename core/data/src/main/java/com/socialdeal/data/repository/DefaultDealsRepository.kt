package com.socialdeal.data.repository

import com.socialdeal.data.mapper.toDeals
import com.socialdeal.network.api.DealsProvider
import com.socialdeal.network.model.APIConstants
import com.socialdeal.network.model.DetailResponse
import com.socialdeal.network.model.WrappedDeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultDealsRepository(private val apiProvider: DealsProvider): DealsRepository {

    private var _deals = MutableStateFlow((emptyList<WrappedDeal>()))
    override val deals: StateFlow<List<WrappedDeal>> = _deals

    override val favorites: StateFlow<List<WrappedDeal>>
         = _deals.map { it.filter { it.favored } }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.WhileSubscribed(1000L),
            initialValue = emptyList()
        )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _deals.value = getDeals()
        }
    }

    private suspend fun getDeals(): List<WrappedDeal> {
        val response = apiProvider.fetchDeals()
         return response.toDeals()
     }

    override suspend fun fetchDealDetails(): DetailResponse =
         apiProvider.fetchDealDetails()


    override fun getImage(imagePath: String):String =
         APIConstants.IMAGE_BASE_URL + imagePath


    override fun toggleFavorite(unique: String) =
        _deals.update {
                currentDeal->
            currentDeal.map {
                    deal ->
                if (deal.data.unique == unique) {
                    deal.copy(favored = !deal.favored )
                } else {
                    deal
                }
            }
    }

    override fun toggleCurrency(dollar: Boolean) {
            _deals.update {
                it.map { deal ->
                    val data = deal.data
                    val prices = data.prices
                    val price = prices.price
                    val currency = if(dollar) {
                        price.currency.copy(symbol = "$", code = "DOL")
                    } else price.currency.copy(symbol = "€", code = "EUR")
                    deal.copy(data = data.copy(prices = prices.copy(price = price.copy(currency = currency))))
                }
            }
        }
}