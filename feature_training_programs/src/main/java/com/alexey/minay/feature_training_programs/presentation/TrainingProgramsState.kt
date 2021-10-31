package com.alexey.minay.feature_training_programs.presentation

import com.alexey.minay.feature_training_programs.domain.TrainingProgram

data class TrainingProgramsState(
    val programs: List<TrainingProgram>
) {

    companion object {
        fun default() = TrainingProgramsState(emptyList())
    }

}