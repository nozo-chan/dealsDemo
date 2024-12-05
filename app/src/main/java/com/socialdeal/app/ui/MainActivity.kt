package com.socialdeal.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.socialdeal.app.DealsApp
import com.socialdeal.app.ui.theme.MyApplicationTheme
import com.socialdeal.app.ui.viewmodel.DealsViewModel
import com.socialdeal.app.ui.viewmodel.DealsViewModelFactory
import com.socialdeal.app.ui.viewmodel.DetailViewModel
import com.socialdeal.app.ui.viewmodel.DetailsViewModelFactory

class MainActivity: ComponentActivity() {
    private val dealsViewModel: DealsViewModel by viewModels {dealsViewModelFactory}
    private val detailsViewModel: DetailViewModel by viewModels { detailsViewModelFactory }
    protected fun application(): DealsApp = application as DealsApp

    private val dealsViewModelFactory: DealsViewModelFactory
        get() = DealsViewModelFactory(application())

    private val detailsViewModelFactory: DetailsViewModelFactory
        get() = DetailsViewModelFactory(application())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme  {
                AppNavigation(dealsViewModel,detailsViewModel)
            }
        }

    }

}