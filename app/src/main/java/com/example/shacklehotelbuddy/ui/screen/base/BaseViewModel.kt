package com.example.shacklehotelbuddy.ui.screen.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<in Action : UiAction, State : UiState, Event : StoreEvent> :
    ViewModel() {

    abstract val state: Flow<State>

    abstract val events: Flow<Event>
}
