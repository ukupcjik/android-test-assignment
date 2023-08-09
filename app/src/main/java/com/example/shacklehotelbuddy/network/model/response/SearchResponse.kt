package com.example.shacklehotelbuddy.network.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: SearchResponseData
)

data class SearchResponseData(
    @SerializedName("propertySearch")
    val propertySearch: PropertySearch
)

data class PropertySearch(
    @SerializedName("properties")
    val properties: List<SearchProperty>
)

data class SearchProperty(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("propertyImage")
    val propertyImageEntity: PropertyImage,
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood,
    @SerializedName("price")
    val price: PropertyPrice,
    @SerializedName("reviews")
    val reviews: Reviews?
)

data class Reviews(
    @SerializedName("score")
    val score: Double?
)

data class PropertyImage(
    @SerializedName("image")
    val image: Image
)

data class Image(
    @SerializedName("url")
    val url: String
)

data class Neighborhood(
    @SerializedName("name")
    val name: String
)

data class PropertyPrice(
    @SerializedName("lead")
    val lead: Lead,
    @SerializedName("priceMessages")
    val priceMessages: List<PriceMessage>?
)

data class Lead(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("currencyInfo")
    val currencyInfo: CurrencyInfo
)

data class CurrencyInfo(
    @SerializedName("symbol")
    val symbol: String?
)

data class PriceMessage(
    @SerializedName("value")
    val value: String?
)
