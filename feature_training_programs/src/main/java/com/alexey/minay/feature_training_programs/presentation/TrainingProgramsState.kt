package com.alexey.minay.feature_training_programs.presentation

import com.alexey.minay.feature_training_programs.domain.TrainingProgram

data class TrainingProgramsState(
    val programs: List<TrainingProgram>,
    val trainingProgramCreationState: TrainingProgramCreationState?,
    val type: Type
) {

    enum class Type {
        PROGRAMS,
        CREATING_PROGRAM
    }

    data class TrainingProgramCreationState(
        val title: String
    )

    companion object {
        fun default() = TrainingProgramsState(
            programs = emptyList(),
            type = Type.PROGRAMS,
            trainingProgramCreationState = null
        )
    }

}