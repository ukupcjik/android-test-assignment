package com.example.shacklehotelbuddy.ui.screen.main

import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.domain.usecase.booking.BookingUseCase
import com.example.shacklehotelbuddy.ui.screen.base.BaseViewModel
import com.example.shacklehotelbuddy.ui.screen.main.store.MainAction
import com.example.shacklehotelbuddy.ui.screen.main.store.MainStore
import com.example.shacklehotelbuddy.ui.screen.main.store.MainState
import com.example.shacklehotelbuddy.ui.screen.main.store.MainStoreEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    bookingUseCase: BookingUseCase
) : BaseViewModel<MainAction, MainState, MainStoreEvent>() {

    private val store = MainStore(
        initial = MainState.initial(),
        bookingUseCase = bookingUseCase,
        viewModelScope = viewModelScope
    )

    override val events: SharedFlow<MainStoreEvent>
        get() = store.events

    override val state: StateFlow<MainState>
        get() = store.state

    init {
        sendAction(MainAction.OnLoadHistory)
    }

    fun sendAction(action: MainAction) {
        store.consumeAction(action)
    }

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }
}
