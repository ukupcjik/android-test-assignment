package com.example.shacklehotelbuddy.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.domain.usecase.search.SearchUseCase
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.base.BaseViewModel
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchContentState
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchAction
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchStore
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchState
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchStoreEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchUseCase: SearchUseCase,
) : BaseViewModel<SearchAction, SearchState, SearchStoreEvent>() {

    private val store = SearchStore(
        initial = SearchState(
            SearchContentState.Initial,
            searchItemsFlow = null,
            query = null
        ),
        searchUseCase = searchUseCase,
        viewModelScope = viewModelScope
    )

    override val state: StateFlow<SearchState>
        get() = store.state

    override val events: Flow<SearchStoreEvent>
        get() = store.events

    fun init(query: BookingItem?) {
        val searchQuery = query ?: return
        sendAction(SearchAction.OnInitData(searchQuery))
    }

    fun sendAction(action: SearchAction) {
        store.consumeAction(action)
    }

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }
}