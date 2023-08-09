package com.example.shacklehotelbuddy.repository.booking

import com.example.shacklehotelbuddy.ui.model.BookingItem

interface BookingRepository {

    fun getSearchHistory(): List<BookingItem>

    fun saveSearchHistory(bookingItem: BookingItem)
}
