package com.socialdeal.app

import android.app.Application
import com.socialdeal.app.api.ApiProvider
import com.socialdeal.app.repository.DefaultDealsRepository


class DealsApp: Application() {

     lateinit var dealsRepository: DefaultDealsRepository
     lateinit var api: ApiProvider


    override fun onCreate() {
        super.onCreate()
        api = ApiProvider()
        dealsRepository = DefaultDealsRepository(api)

    }
}