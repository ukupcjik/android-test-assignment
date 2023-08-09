package com.example.shacklehotelbuddy.ui.screen.main.store

import com.example.shacklehotelbuddy.domain.usecase.booking.BookingUseCase
import com.example.shacklehotelbuddy.ui.screen.base.BaseStore
import com.example.shacklehotelbuddy.ui.utils.update
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainStore @Inject constructor(
    val bookingUseCase: BookingUseCase,
    val viewModelScope: CoroutineScope,
    initial: MainState,
) : BaseStore<MainAction, MainState, MainStoreEvent>(initialVal = initial) {

    override fun consumeAction(action: MainAction) {
        when (action) {
            is MainAction.OnBookingDataChanged -> onDataChangeAction(action)
            is MainAction.OnApplyRecentBooking -> onApplyRecentBookingAction(action)
            is MainAction.OnLoadHistory -> onLoadHistoryAction()
            is MainAction.OnSearchAction -> onSearchAction()
        }
    }

    private fun onDataChangeAction(action: MainAction.OnBookingDataChanged) {
        state.update { state ->
            state.copy(bookingData = action.bookingItem)
        }
    }

    private fun onApplyRecentBookingAction(action: MainAction.OnApplyRecentBooking) {
        state.update { state ->
            state.copy(bookingData = action.historyItem)
        }
    }

    private fun onLoadHistoryAction() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = bookingUseCase.updateHistory()
            state.update { state ->
                state.copy(historyData = history)
            }
        }
    }

    private fun onSearchAction() {
        val item = state.value.bookingData
        bookingUseCase.saveHistory(item)
        state.update { state ->
            state.copy(historyData = bookingUseCase.updateHistory())
        }
        val bookingNavigationJson: String = Gson().toJson(item)
        sendEvent(MainStoreEvent.OnNavigationToSearch(bookingNavigationJson))
    }
}
