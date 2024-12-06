package com.socialdeal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdeal.data.repository.DealsRepository
import com.socialdeal.network.model.WrappedDeal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val dealsRepository: DealsRepository) : ViewModel() {

    val deals : StateFlow<List<WrappedDeal>> = dealsRepository.deals
    private var _details = MutableStateFlow("")

    val details: StateFlow<String> = _details

    init {
        getDealDetails()
    }

    private fun getDealDetails() {
        viewModelScope.launch {
            _details.value = dealsRepository.fetchDealDetails().description
        }
    }

    fun getImageUri(imagePath:String): String =
        dealsRepository.getImage(imagePath)

}