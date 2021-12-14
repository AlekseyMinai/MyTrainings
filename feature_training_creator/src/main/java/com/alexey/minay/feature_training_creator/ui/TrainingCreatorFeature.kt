package com.alexey.minay.feature_training_creator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorState
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore

@Composable
fun TrainingCreatorFeature(
    store: TrainingCreatorStore,
    onBackPressed: () -> Unit
) {
    val state by store.state.collectAsState()
    when (state.type) {
        TrainingCreatorState.Type.TRAINING_CREATOR -> TrainingCreatorScreen(
            onBackPressed = onBackPressed,
            store = store
        )
        TrainingCreatorState.Type.EXERCISE_SELECTOR ->
            ExerciseSelectorScreen(store = store)
    }
}