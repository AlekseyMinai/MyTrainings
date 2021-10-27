package com.alexey.minay.feature_training.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alexey.minay.feature_training.R
import com.alexey.minay.feature_training.presentation.EditSetDialogState

@Composable
fun EditSetDialog(
    state: EditSetDialogState,
    onWeightChanged: (String?) -> Unit,
    onCountChanged: (String?) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            val title = when {
                state.isEditing -> stringResource(R.string.edit)
                else -> stringResource(R.string.add_set)
            }
            Text(text = title)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = (state.weight ?: "").toString(),
                    onValueChange = { EditTextUtils.prepareWeight(it, onWeightChanged) },
                    label = { Text(stringResource(R.string.weight)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = (state.count ?: "").toString(),
                    onValueChange = { EditTextUtils.prepareCount(it, onCountChanged) },
                    label = { Text(stringResource(R.string.repeat_count)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(R.string.apply))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancel))
            }
            Button(onClick = onDelete) {
                Text(text = stringResource(R.string.delete))
            }
        }
    )
}