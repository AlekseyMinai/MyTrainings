package com.alexey.minay.core_ui

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class Store<State, Intent, Event>(
    initState: State
) {

    val state: StateFlow<State> by uiLazy { mState.asStateFlow() }
    val event: SharedFlow<Event> by uiLazy { mEvent.asSharedFlow() }

    private val mState = MutableStateFlow(initState)
    private val mEvent = MutableSharedFlow<Event>()
    private val mCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    suspend fun accept(intent: Intent) {
        mCoroutineScope.launch { execute(intent) }
    }

    protected abstract suspend fun execute(intent: Intent)

    protected suspend fun modify(modifier: State.() -> State) {
        mState.emit(mState.value.modifier())
    }

    protected suspend fun send(event: Event) {
        mCoroutineScope.launch {
            mEvent.emit(event)
        }
    }

    fun onClear() {
        mCoroutineScope.cancel()
    }

}