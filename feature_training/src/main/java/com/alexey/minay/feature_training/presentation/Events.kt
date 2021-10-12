package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.ExerciseId

sealed interface TrainingIntent {
    class AddSet(
        val exerciseId: ExerciseId,
        val weight: Int,
        val count: Int
    ) : TrainingIntent
}

sealed interface TrainingEvent