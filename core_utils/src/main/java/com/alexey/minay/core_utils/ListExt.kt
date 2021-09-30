package com.alexey.minay.core_utils

fun <T>List<T>.asString(decorate: (String) -> String = { it }) : String {
    var sum = ""
    forEach { sum += decorate(it.toString()) }
    return sum
}