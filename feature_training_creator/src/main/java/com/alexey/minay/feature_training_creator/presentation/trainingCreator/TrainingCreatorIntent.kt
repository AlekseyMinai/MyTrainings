package com.alexey.minay.feature_training_creator.presentation.trainingCreator

import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId

sealed interface TrainingCreatorIntent {
    class ChangeExerciseSelection(val exerciseId: ExerciseId) : TrainingCreatorIntent
    class ChangeExpandState(val muscleGroupId: MuscleGroupId) : TrainingCreatorIntent
    object OpenExerciseSelectorScreen : TrainingCreatorIntent
    object OpenTrainingCreatorScreen : TrainingCreatorIntent
}