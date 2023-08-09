package com.example.shacklehotelbuddy.repository.search

import com.example.shacklehotelbuddy.network.model.query.SearchQuery
import com.example.shacklehotelbuddy.ui.model.SearchItem
import kotlinx.coroutines.flow.StateFlow

interface SearchRepository {

    val searchItemsFlow: StateFlow<List<SearchItem>>

    suspend fun getHostelsList(searchQuery: SearchQuery)
}
