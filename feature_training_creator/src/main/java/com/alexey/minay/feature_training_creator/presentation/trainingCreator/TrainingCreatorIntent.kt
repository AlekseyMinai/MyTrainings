package com.alexey.minay.feature_training_creator.presentation.trainingCreator

sealed interface TrainingCreatorIntent {
    object OpenExerciseSelectorScreen : TrainingCreatorIntent
    object OpenTrainingCreatorScreen : TrainingCreatorIntent
}