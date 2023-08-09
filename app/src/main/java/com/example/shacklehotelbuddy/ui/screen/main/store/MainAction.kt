package com.example.shacklehotelbuddy.ui.screen.main.store

import androidx.compose.runtime.Immutable
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.base.UiAction

@Immutable
sealed class MainAction : UiAction {

    data class OnBookingDataChanged(val bookingItem: BookingItem) : MainAction()

    object OnSearchAction : MainAction()

    object OnLoadHistory : MainAction()

    data class OnApplyRecentBooking(val historyItem: BookingItem) : MainAction()
}
