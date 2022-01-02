package com.alexey.minay.feature_training_programs.presentation

sealed interface ITrainingProgramsIntent {
    class ChangeNewTrainingProgramTitle(val title: String) : ITrainingProgramsIntent
    object DismissTrainingCreation : ITrainingProgramsIntent
    object ConfirmTrainingCreation : ITrainingProgramsIntent
    object CreateTrainingProgram : ITrainingProgramsIntent
}

sealed interface ITrainingProgramsEvent