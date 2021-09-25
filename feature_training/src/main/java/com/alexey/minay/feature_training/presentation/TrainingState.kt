package com.alexey.minay.feature_training.presentation

import com.alexey.minay.feature_training.domain.Training

data class TrainingState(
    val training: Training?
) {
    companion object {
        fun default() = TrainingState(null)
    }
}