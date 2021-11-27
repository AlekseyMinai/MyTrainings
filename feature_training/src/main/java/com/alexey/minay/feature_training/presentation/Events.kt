package com.alexey.minay.feature_training.presentation

import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.feature_training.domain.TrainingSet

sealed interface TrainingIntent {
    object AddSet : TrainingIntent
    object CancelAddingSet : TrainingIntent
    object DeleteSet : TrainingIntent

    class CreateSet(val exerciseId: ExerciseId) : TrainingIntent
    class ChangeWeight(val weight: String?) : TrainingIntent
    class ChangeCount(val count: String?) : TrainingIntent
    class EditSet(val trainingSet: TrainingSet, val exerciseId: ExerciseId) : TrainingIntent
}

sealed interface TrainingEvent