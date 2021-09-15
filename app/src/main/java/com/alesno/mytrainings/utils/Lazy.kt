package com.alesno.mytrainings.utils

fun <T> uiLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)