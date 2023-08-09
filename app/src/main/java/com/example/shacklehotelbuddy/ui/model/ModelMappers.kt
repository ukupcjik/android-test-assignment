package com.example.shacklehotelbuddy.ui.model

import com.example.shacklehotelbuddy.network.model.response.SearchProperty
import com.example.shacklehotelbuddy.network.model.query.CheckInDate
import com.example.shacklehotelbuddy.network.model.query.CheckOutDate
import com.example.shacklehotelbuddy.network.model.query.Children
import com.example.shacklehotelbuddy.network.model.query.Destination
import com.example.shacklehotelbuddy.network.model.query.Filters
import com.example.shacklehotelbuddy.network.model.query.Price
import com.example.shacklehotelbuddy.network.model.query.Room
import com.example.shacklehotelbuddy.network.model.query.SearchQuery

/**
 * Extensions with hardcoded values
*/
fun BookingItem.toSearchQuery(): SearchQuery {

    val child: MutableList<Children> = mutableListOf()
    this.childrenCount.forEach { _ ->
        child.add(
            Children(
                age = 7
            )
        )
    }
    val room = Room(
        adults = this.adultCount.toIntOrNull() ?: 0,
        children = child.toList()
    )
    val rooms: MutableList<Room> = mutableListOf()
    rooms.add(room)

    return SearchQuery(
        checkInDate = CheckInDate(
            day = checkInDate.day,
            month = checkInDate.month,
            year = checkInDate.year
        ),
        checkOutDate = CheckOutDate(
            day = checkOutDate.day,
            month = checkOutDate.month,
            year = checkOutDate.year
        ),
        rooms = rooms,
        resultsSize = 30,
        filters = Filters(
            price = Price(
                min = 100,
                max = 3000,
            )
        ),
        destination = Destination(
            regionId = "6054439"
        ),
        resultsStartingIndex = 0,
        sort = "PRICE_LOW_TO_HIGH",
        locale = "en_US",
        eapid = 1,
        siteId = 300000001,
        currency = "USD"
    )
}

fun SearchProperty.toPresentation(): SearchItem {
    return SearchItem(
        id = this.id,
        name = this.name,
        imageUrl = this.propertyImageEntity.image.url,
        rating = this.reviews?.score ?: 0.0,
        price = this.price.lead.amount?:0.0,
        pricePer = this.price.priceMessages?.firstOrNull()?.value ?: "",
        priceCurrencySymbol = this.price.lead.currencyInfo.symbol?:"",
        city = this.neighborhood.name,
        country = "Vietnam"
    )
}
