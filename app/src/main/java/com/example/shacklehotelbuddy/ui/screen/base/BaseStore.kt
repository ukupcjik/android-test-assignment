package com.example.shacklehotelbuddy.ui.screen.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface Store<Action, State, Event> {
    val state: StateFlow<State>
    val events: SharedFlow<Event>

    fun consumeAction(action: Action)
}

interface DisposableStore<Action, State, Event> : Store<Action, State, Event> {

    val storeScope: CoroutineScope

    fun dispose()
}

abstract class BaseStore<Action : UiAction, State : UiState, Event : StoreEvent>(initialVal: State) :
    DisposableStore<Action, State, Event> {

    override val storeScope: CoroutineScope =
        CoroutineScope(context = SupervisorJob() + Dispatchers.Main.immediate)

    override val state: MutableStateFlow<State>

    final override val events: MutableSharedFlow<Event>

    init {
        @Suppress("LeakingThis")
        state = MutableStateFlow(initialVal)
        events = MutableSharedFlow()
    }

    fun sendEvent(event: Event) {
        storeScope.launch { events.emit(event) }
    }

    override fun dispose() {
        storeScope.coroutineContext.cancel()
    }
}

interface UiState

interface UiAction

interface StoreEvent
