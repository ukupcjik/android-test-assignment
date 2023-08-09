package com.example.shacklehotelbuddy.network

import com.example.shacklehotelbuddy.network.ApiConfig.AUTH_HEADER
import com.example.shacklehotelbuddy.network.ApiConfig.AUTH_HEADER_VALUE
import com.example.shacklehotelbuddy.network.ApiConfig.HOST_HEADER
import com.example.shacklehotelbuddy.network.ApiConfig.HOST_HEADER_VALUE
import com.example.shacklehotelbuddy.network.model.response.SearchResponse
import com.example.shacklehotelbuddy.network.model.query.SearchQuery
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RapidApiClient {

    @Headers(
        "Content-Type: application/json",
        "$AUTH_HEADER: $AUTH_HEADER_VALUE",
        "$HOST_HEADER: $HOST_HEADER_VALUE"
    )
    @POST("properties/v2/list")
    suspend fun getHostelsList(
        @Body searchParams: SearchQuery,
    ): SearchResponse
}
