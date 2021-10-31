package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.feature_training_list.domain.TrainingType

data class TrainingListState(
    val programId: TrainingProgramId,
    val trainings: List<TrainingType>
) {
    companion object {
        fun default(programId: TrainingProgramId) = TrainingListState(
            programId = programId,
            trainings = emptyList()
        )
    }
}