package com.socialdeal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.socialdeal.app.DealsApp
import com.socialdeal.app.repository.DefaultDealsRepository

class DetailsViewModelFactory(private val application: DealsApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(
                DefaultDealsRepository(apiProvider = application.api)
            ) as T
        } else {
            throw Error("Unable to create a ProgressViewModelFactory, An unsupported type was requested")
        }
    }
}