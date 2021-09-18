package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.TrainingExerciseId

sealed interface TrainingIntent {
    class AddSet(val id: TrainingExerciseId, val weight: Int, val count: Int) : TrainingIntent
}

sealed interface TrainingEvent