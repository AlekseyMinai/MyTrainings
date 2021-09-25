package com.alexey.minay.feature_training_list.presentation

import com.alexey.minay.feature_training_list.domain.TrainingInfo

data class TrainingListState(
    val trainings: List<TrainingInfo>
) {
    companion object {
        fun default() = TrainingListState(emptyList())
    }
}