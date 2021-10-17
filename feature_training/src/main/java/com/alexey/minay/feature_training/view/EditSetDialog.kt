package com.alexey.minay.feature_training.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import com.alexey.minay.feature_training.presentation.EditSetDialogState

@Composable
fun EditSetDialog(
    state: EditSetDialogState,
    onWeightChanged: (Int) -> Unit,
    onCountChanged: (Int) -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            val title = when {
                state.isEditing -> "Редактировать"
                else -> "Добавить подход"
            }
            Text(text = title)
        },
        text = {
            Column {
                Text(text = "Вес")
                TextField(
                    value = (state.weight ?: "").toString(),
                    onValueChange = { onWeightChanged(it.toInt()) }
                )
                Text(text = "Повторения")
                TextField(
                    value = (state.count ?: "").toString(),
                    onValueChange = { onCountChanged(it.toInt()) }
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Подтвердить")
            }
        },
        dismissButton = {
            Button(onClick = { /*TODO*/ }) {

            }
        }
    )
}