package com.socialdeal.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.socialdeal.app.DealsApp
import com.socialdeal.app.api.ApiProvider
import com.socialdeal.app.repository.DefaultDealsRepository
import com.socialdeal.app.ui.viewmodel.DealsViewModel
import com.socialdeal.app.ui.viewmodel.DealsViewModelFactory

class MainActivity: ComponentActivity() {
    private val myViewModel: DealsViewModel by viewModels {dealsViewModelFactory}
    protected fun application(): DealsApp = application as DealsApp

    private val dealsViewModelFactory: DealsViewModelFactory
        get() = DealsViewModelFactory(application())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Use MaterialTheme for consistent styling
            MaterialTheme {
                AppNavigation(myViewModel)
            }
        }

    }

}