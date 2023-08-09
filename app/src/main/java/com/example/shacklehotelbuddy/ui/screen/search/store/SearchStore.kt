package com.example.shacklehotelbuddy.ui.screen.search.store

import com.example.shacklehotelbuddy.domain.usecase.search.SearchUseCase
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.base.BaseStore
import com.example.shacklehotelbuddy.ui.utils.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchStore @Inject constructor(
    val searchUseCase: SearchUseCase,
    val viewModelScope: CoroutineScope,
    initial: SearchState,
) : BaseStore<SearchAction, SearchState, SearchStoreEvent>(initialVal = initial) {

    private var loadJob: Job? = null

    override fun consumeAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnInitData -> onInitDataAction(action)
            is SearchAction.OnRefresh -> onRefreshAction()
        }
    }

    private fun onInitDataAction(action: SearchAction.OnInitData) {
        state.update { state ->
            state.copy(
                query = action.query,
                searchItemsFlow = { searchUseCase.searchItemsFlow })
        }
        loadData(query = action.query)
    }

    private fun onRefreshAction() {
        loadData(query = state.value.query)
    }

    private fun loadData(query: BookingItem?) {
        if (query == null) {
            onError()
            return
        }
        loadJob?.cancel()
        onLoading()
        loadJob = viewModelScope.launch {
            try {
                searchUseCase.getHostelsList(query)
                onLoadContentSuccessfully()
            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onError() {
        state.update { state ->
            state.copy(searchContentState = SearchContentState.Error)
        }
    }

    private fun onLoading() {
        state.update { state ->
            state.copy(searchContentState = SearchContentState.Loading)
        }
    }

    private fun onLoadContentSuccessfully() {
        state.update { state ->
            state.copy(searchContentState = SearchContentState.Content)
        }
    }
}
