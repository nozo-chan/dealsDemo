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
        return try {
            client.get(APIConstants.API_URL).body()
          } finally {
            client.close()
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

        return try {
            client.get(APIConstants.DETAILS_URL).body()
        } finally {
            client.close()
        }
    }

    override suspend fun getFullImageUrl(image:String): String {
        return APIConstants.IMAGE_PREFIX + image
    }



}