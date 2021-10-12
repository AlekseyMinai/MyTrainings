package com.alexey.minay.feature_training.presentation

import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.Training

data class TrainingState(
    val training: Training
) {
    companion object {
        fun default(trainingTypeId: TrainingTypeId) =
            TrainingState(Training.default(trainingTypeId))
    }
}