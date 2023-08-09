package com.example.shacklehotelbuddy.ui.screen.main.store

import androidx.compose.runtime.Immutable
import com.example.shacklehotelbuddy.ui.screen.base.StoreEvent

@Immutable
sealed class MainStoreEvent : StoreEvent {

    data class OnNavigationToSearch(val bookingItemJSON: String) : MainStoreEvent()
}