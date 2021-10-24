package com.alexey.minay.feature_training.view

object EditTextUtils {

    fun prepareWeight(value: String, onValueChanged: (String?) -> Unit) =
        when {
            value.isBlank() -> onValueChanged(null)
            value.last() == '.' ->
                "${value}0".toFloatOrNull()?.let { onValueChanged(value) }
            value.last() == ',' ->
                "${value.replace(",", ".")}0".toFloatOrNull()?.let {
                    onValueChanged(value.replace(",", "."))
                }
            value.split(".").let { it.size > 1 && it[1].length > 2 } -> {
                val splitValue = value.split(".")
                val newValue = splitValue[0] + "." + splitValue[1].substring(0..1)
                onValueChanged(newValue)
            }
            else -> value.toFloatOrNull()?.let { onValueChanged(value) }
        }

    fun prepareCount(value: String, onValueChanged: (String?) -> Unit) =
        when {
            value.isBlank() -> onValueChanged(null)
            else -> value.toIntOrNull()?.let { onValueChanged(value) }
        }

}