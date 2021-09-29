package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.feature_training_list.domain.TrainingType

data class TrainingListState(
    val trainings: List<TrainingType>
) {
    companion object {
        fun default() = TrainingListState(emptyList())
    }
}