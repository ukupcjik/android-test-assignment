package com.example.shacklehotelbuddy.domain.usecase.search

import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.SearchItem
import kotlinx.coroutines.flow.StateFlow

interface SearchUseCase {

    val searchItemsFlow: StateFlow<List<SearchItem>>

    suspend fun getHostelsList(query: BookingItem)
}
