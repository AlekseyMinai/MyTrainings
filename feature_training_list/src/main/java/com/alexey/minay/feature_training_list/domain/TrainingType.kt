package com.alexey.minay.feature_training_list.domain

import com.alexey.minay.core_training.TrainingTypeId

data class TrainingType(
    val id: TrainingTypeId,
    val title: String
)