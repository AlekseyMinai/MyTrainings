package com.alexey.minay.feature_training_list.domain

import com.alexey.minay.core_training.TrainingInfoId

data class TrainingInfo(
    val id: TrainingInfoId,
    val name: String
)