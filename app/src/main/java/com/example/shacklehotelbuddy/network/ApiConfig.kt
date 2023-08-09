package com.example.shacklehotelbuddy.network

import com.example.shacklehotelbuddy.BuildConfig

object ApiConfig {
    const val BASE_URL = BuildConfig.RAPID_API_BASE_URL
    const val AUTH_HEADER = "X-RapidAPI-Key"
    const val AUTH_HEADER_VALUE = BuildConfig.RAPID_API_KEY
    const val HOST_HEADER = "X-RapidAPI-Host"
    const val HOST_HEADER_VALUE = BuildConfig.RAPID_APP_HOST
}
