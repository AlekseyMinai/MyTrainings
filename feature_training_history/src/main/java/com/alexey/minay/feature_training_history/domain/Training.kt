package com.alexey.minay.feature_training_history.domain

import com.alexey.minay.core_training.TrainingId
import java.util.*

data class Training(
    val id: TrainingId,
    val title: String,
    val date: Date
)