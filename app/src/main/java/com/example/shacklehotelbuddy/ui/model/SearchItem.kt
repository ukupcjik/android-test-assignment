package com.example.shacklehotelbuddy.ui.model

import androidx.compose.runtime.Stable

@Stable
data class SearchItem(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val price: Double,
    val pricePer: String,
    val priceCurrencySymbol: String,
    val city: String,
    val country: String,
)
