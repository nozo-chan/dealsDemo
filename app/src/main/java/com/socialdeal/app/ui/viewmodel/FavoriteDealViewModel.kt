package com.socialdeal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.socialdeal.app.model.WrappedDeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteDealViewModel: ViewModel() {

    private var _favorites = MutableStateFlow(emptyList<WrappedDeals>())
    val favorites: StateFlow<List<WrappedDeals>> = _favorites


}