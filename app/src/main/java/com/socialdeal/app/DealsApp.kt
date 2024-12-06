package com.socialdeal.app

import android.app.Application
import com.socialdeal.data.repository.DefaultDealsRepository
import com.socialdeal.network.api.ApiProvider

class DealsApp: Application() {

     private lateinit var dealsRepository: DefaultDealsRepository
     lateinit var api: ApiProvider

    override fun onCreate() {
        super.onCreate()
        api = ApiProvider()
        dealsRepository = DefaultDealsRepository(api)

    }
}