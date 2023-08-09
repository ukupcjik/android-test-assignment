package com.example.shacklehotelbuddy.ui.screen.search.store

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.SearchItem
import com.example.shacklehotelbuddy.ui.screen.base.UiState
import kotlinx.coroutines.flow.StateFlow

@Stable
data class SearchState(
    val searchContentState: SearchContentState,
    val searchItemsFlow: (() -> StateFlow<List<SearchItem>>)?,
    val query: BookingItem?
) : UiState

@Immutable
sealed interface SearchContentState {

    object Initial : SearchContentState

    object Loading : SearchContentState

    object Content : SearchContentState

    object Error : SearchContentState
}
