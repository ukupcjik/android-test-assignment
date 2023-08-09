package com.example.shacklehotelbuddy.ui.screen.main.store

import androidx.compose.runtime.Stable
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.CheckDate
import com.example.shacklehotelbuddy.ui.screen.base.UiState

@Stable
data class MainState(
    val bookingData: BookingItem,
    val historyData: List<BookingItem>,
) : UiState {

    val isSearchButtonDisabled: Boolean
        get() =
            bookingData.adultCount.isBlank() ||
                    bookingData.checkInDate.day == 0 ||
                    bookingData.checkOutDate.day == 0

    val isRecentHistoryAvailable: Boolean
        get() = historyData.isEmpty().not()

    companion object {
        fun initial() = MainState(
            bookingData = BookingItem(
                id = 0,
                checkInDate = CheckDate(),
                checkOutDate = CheckDate(),
                childrenCount = "",
                adultCount = ""
            ),
            historyData = emptyList()
        )
    }
}
