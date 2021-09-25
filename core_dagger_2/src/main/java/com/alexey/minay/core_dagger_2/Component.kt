package com.alexey.minay.core_dagger_2

import java.lang.ref.WeakReference

inline fun <reified Comp> initAndGetDaggerComponentWithDCL(
    weakRef: WeakReference<Comp>?,
    initFactory: () -> Comp
): Comp {
    var comp = weakRef?.get()
    if (comp == null) {
        synchronized(Comp::class.java) {
            comp = weakRef?.get()
            if (comp == null) {
                comp = initFactory()
            }
        }
    }
    return comp!!
}

inline fun <Comp> initAndGetDaggerComponent(
    weakRef: WeakReference<Comp>?,
    initFactory: () -> Comp
): Comp {
    return weakRef?.get() ?: initFactory()
}