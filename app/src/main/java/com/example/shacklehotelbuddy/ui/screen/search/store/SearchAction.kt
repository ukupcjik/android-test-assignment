package com.example.shacklehotelbuddy.ui.screen.search.store

import androidx.compose.runtime.Immutable
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.base.UiAction

@Immutable
sealed class SearchAction : UiAction {

    data class OnInitData(val query: BookingItem) : SearchAction()

    object OnRefresh : SearchAction()
}
