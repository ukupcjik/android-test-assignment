package com.example.shacklehotelbuddy.domain.usecase.search

import com.example.shacklehotelbuddy.repository.search.SearchRepository
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.toSearchQuery
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchUseCase {

    override val searchItemsFlow = searchRepository.searchItemsFlow

    override suspend fun getHostelsList(query: BookingItem) =
        searchRepository.getHostelsList(query.toSearchQuery())
}
