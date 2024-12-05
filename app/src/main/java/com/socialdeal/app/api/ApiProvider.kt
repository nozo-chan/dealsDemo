package com.socialdeal.app.api

import com.socialdeal.app.model.APIConstants
import com.socialdeal.app.model.DetailResponse
import com.socialdeal.app.model.ApiResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiProvider: DealsProvider {

   override suspend fun fetchDeals(): ApiResponse {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        return client.use {
            it.get(APIConstants.API_URL).body()
        }
    }

    override suspend fun fetchDealDetails(): DetailResponse {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        return client.use {
            it.get(APIConstants.DETAILS_URL).body()
        }
    }
}