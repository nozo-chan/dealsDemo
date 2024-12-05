package com.socialdeal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.socialdeal.app.model.WrappedDeal
import com.socialdeal.app.repository.DealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DealsViewModel(
    private val dealsRepository: DealsRepository,
): ViewModel() {
    val deals : StateFlow<List<WrappedDeal>> = dealsRepository.deals
    private var _dollar = MutableStateFlow(false)

    fun getImageUri(imagePath:String): String =
         dealsRepository.getImage(imagePath)

    fun getFavorites() = dealsRepository.favorites

    fun toggleFavorite(unique: String) =
      dealsRepository.toggleFavorite(unique)

    fun toggleCurrency() {
        _dollar.value = !_dollar.value
        dealsRepository.toggleCurrency(_dollar.value)
    }

}






