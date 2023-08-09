package com.example.shacklehotelbuddy.domain.usecase.booking

import com.example.shacklehotelbuddy.repository.booking.BookingRepository
import com.example.shacklehotelbuddy.ui.model.BookingItem
import javax.inject.Inject

class BookingUseCaseImpl @Inject constructor(
    private val bookingRepository: BookingRepository
) : BookingUseCase {

    override fun updateHistory(): List<BookingItem> {
        return bookingRepository.getSearchHistory()
    }

    override fun saveHistory(bookingItem: BookingItem) {
        bookingRepository.saveSearchHistory(bookingItem)
    }
}
