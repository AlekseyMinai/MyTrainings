package com.alexey.minay.core_ui.mvi

fun interface IReducer<TResult : Any, TState : Any> {
    fun TState.reduce(result: TResult): TState
}