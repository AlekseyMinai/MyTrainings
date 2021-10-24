package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.ExerciseId

sealed interface TrainingIntent {
    object AddSet : TrainingIntent
    object CancelAddingSet : TrainingIntent

    class OpenEditSetDialog(
        val exerciseId: ExerciseId
    ) : TrainingIntent

    class ChangeWeight(
        val weight: String?
    ) : TrainingIntent

    class ChangeCount(
        val count: String?
    ) : TrainingIntent
}

sealed interface TrainingEvent