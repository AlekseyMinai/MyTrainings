package com.alexey.minay.core_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class SimpleStore<State, Intent, Event>(
    initState: State
) : ViewModel() {

    val state: StateFlow<State> by uiLazy { mState.asStateFlow() }
    val event: SharedFlow<Event> by uiLazy { mEvent.asSharedFlow() }

    private val mState = MutableStateFlow(initState)
    private val mEvent = MutableSharedFlow<Event>()

    fun accept(intent: Intent) {
        viewModelScope.launch { execute(intent) }
    }

    protected abstract suspend fun execute(intent: Intent)

    protected fun getState() = state.value

    protected fun modify(modifier: State.() -> State) {
        mState.value = mState.value.modifier()
    }

    protected suspend fun send(event: Event) {
        viewModelScope.launch {
            mEvent.emit(event)
        }
    }

}