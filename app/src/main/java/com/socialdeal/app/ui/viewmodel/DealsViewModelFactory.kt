package com.socialdeal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.socialdeal.app.DealsApp
import com.socialdeal.data.repository.DefaultDealsRepository


class DealsViewModelFactory(private val application: DealsApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DealsViewModel::class.java)) {
            DealsViewModel(
                DefaultDealsRepository(apiProvider = application.api)
                ) as T
        } else {
            throw Error("Unable to create a ProgressViewModelFactory, An unsupported type was requested")
        }
    }
}