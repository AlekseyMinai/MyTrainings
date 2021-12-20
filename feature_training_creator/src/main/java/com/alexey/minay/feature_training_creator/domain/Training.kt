package com.alexey.minay.feature_training_creator.domain

import com.alexey.minay.core_training.ExerciseId

data class Training(
    val title: String,
    val exercises: List<ExerciseId>
)