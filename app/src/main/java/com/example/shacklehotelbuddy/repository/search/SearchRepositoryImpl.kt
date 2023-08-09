package com.example.shacklehotelbuddy.repository.search

import com.example.shacklehotelbuddy.network.RapidApiClient
import com.example.shacklehotelbuddy.network.model.query.SearchQuery
import com.example.shacklehotelbuddy.ui.model.SearchItem
import com.example.shacklehotelbuddy.ui.model.toPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiClient: RapidApiClient
) : SearchRepository {
    // TODO Alexey Becker: todo pagination
    override val searchItemsFlow: MutableStateFlow<List<SearchItem>> = MutableStateFlow(
        emptyList()
    )

    override suspend fun getHostelsList(searchQuery: SearchQuery) {
        withContext(Dispatchers.IO) {
            val result = apiClient.getHostelsList(
                searchParams = searchQuery
            ).data.propertySearch.properties
                .map { rawItem ->
                    rawItem.toPresentation()
                }
            searchItemsFlow.emit(result)
        }
    }
}
