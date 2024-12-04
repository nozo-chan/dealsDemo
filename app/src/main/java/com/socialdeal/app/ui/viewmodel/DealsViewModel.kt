package com.socialdeal.app.ui.viewmodel
import kotlinx.coroutines.flow.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdeal.app.model.APIConstants
import com.socialdeal.app.model.WrappedDeals
import com.socialdeal.app.repository.GetDealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DealsViewModel(
    private val getDealsRepository: GetDealsRepository,
): ViewModel() {

    private var _deals = MutableStateFlow((emptyList<WrappedDeals>()))
    val deals : StateFlow<List<WrappedDeals>> = _deals

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String> get() = _imageUrl

    private var _details = MutableStateFlow("")
    val details: StateFlow<String> get() = _details

    init {
        getDeals()
        getDealDetails()
    }

    private fun getDeals() {
        viewModelScope.launch{
            val response=  getDealsRepository.getDeals()
            _deals.value = response
        }
    }

    fun getFavorites() = deals.map { it.filter { it.favorites } }.stateIn(
        scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(1000L),
    initialValue = emptyList()
    )

    fun toggleFavorites(unique: String) {
        _deals.update {
                currentDeal->
            currentDeal.map {
                    deal ->
                if (deal.deal.unique == unique) {
                    deal.copy(favorites = !deal.favorites )
                } else {
                    deal
                }
            }
        }

    }

    private fun getDealDetails() {
        viewModelScope.launch {
            _details.value = getDealsRepository.fetchDealDetails().description
        }
    }

    fun getImage(image:String): String {
        return APIConstants.IMAGE_PREFIX + image
    }

}






