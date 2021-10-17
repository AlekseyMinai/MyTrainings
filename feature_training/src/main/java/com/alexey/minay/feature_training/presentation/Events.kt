package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.ExerciseId

sealed interface TrainingIntent {
    object AddSet : TrainingIntent

    class OpenEditSetDialog(
        val exerciseId: ExerciseId
    ) : TrainingIntent

    class ChangeWeight(
        val weight: Int
    ) : TrainingIntent

    class ChangeCount(
        val count: Int
    ) : TrainingIntent
}

sealed interface TrainingEvent {
/*    class OpenEditSetDialog(
        val exerciseId: ExerciseId
    ) : TrainingEvent*/
}