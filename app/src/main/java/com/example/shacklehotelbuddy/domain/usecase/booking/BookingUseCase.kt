package com.example.shacklehotelbuddy.domain.usecase.booking

import com.example.shacklehotelbuddy.ui.model.BookingItem

interface BookingUseCase {

    fun updateHistory(): List<BookingItem>

    fun saveHistory(bookingItem: BookingItem)
}
