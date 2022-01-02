package com.alexey.minay.feature_training_programs.view

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
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingProgramCreatorDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onChangeTitle: (String) -> Unit,
    title: String
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = stringResource(id = RCoreUi.string.training_program_creation))
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { onChangeTitle(it) },
                    label = { Text(stringResource(id = RCoreUi.string.input_program_title)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(RCoreUi.string.apply))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(RCoreUi.string.cancel))
            }
        }
    )
}