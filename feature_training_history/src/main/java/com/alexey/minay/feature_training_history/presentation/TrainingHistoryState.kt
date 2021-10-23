package com.alexey.minay.feature_training_history.presentation

import com.alexey.minay.feature_training_history.domain.Training

data class TrainingHistoryState(
    val trainings: List<Training>
) {
    companion object {
        fun default() = TrainingHistoryState(
            trainings = emptyList()
        )
    }
}