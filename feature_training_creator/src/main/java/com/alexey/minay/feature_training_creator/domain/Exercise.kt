package com.alexey.minay.feature_training_creator.domain

import com.alexey.minay.core_training.ExerciseId

data class Exercise(
    val id: ExerciseId,
    val title: String
)