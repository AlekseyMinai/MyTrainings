package com.alexey.minay.feature_training_creator.presentation

import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId

sealed interface TrainingCreatorAction {
    class ChangeExerciseSelection(val exerciseId: ExerciseId) : TrainingCreatorAction
    class ChangeExpandState(val muscleGroupId: MuscleGroupId) : TrainingCreatorAction
    object OpenExerciseSelectorScreen : TrainingCreatorAction
    object OpenTrainingCreatorScreen : TrainingCreatorAction
}