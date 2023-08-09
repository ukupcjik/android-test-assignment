package com.example.shacklehotelbuddy.network.model.query

data class SearchQuery(
    val checkInDate: CheckInDate,
    val checkOutDate: CheckOutDate,
    val currency: String,
    val destination: Destination,
    val eapid: Int,
    val filters: Filters,
    val locale: String,
    val resultsSize: Int,
    val resultsStartingIndex: Int,
    val rooms: List<Room>,
    val siteId: Int,
    val sort: String
)

data class Room(
    val adults: Int,
    val children: List<Children>
)

data class Price(
    val max: Int,
    val min: Int,
)

data class Filters(
    val price: Price
)

data class Destination(
    val regionId: String,
)

data class Children(
    val age: Int,
)

data class CheckOutDate(
    val day: Int,
    val month: Int,
    val year: Int
)

data class CheckInDate(
    val day: Int,
    val month: Int,
    val year: Int
)
