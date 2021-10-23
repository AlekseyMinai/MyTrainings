package com.alexey.minay.core_utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toStringFormat(): String {
    val format = SimpleDateFormat("dd MMMM yyyy hh:mm")
    return format.format(this)
}