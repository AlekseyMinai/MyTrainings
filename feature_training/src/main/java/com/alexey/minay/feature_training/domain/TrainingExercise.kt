package com.alexey.minay.feature_training.domain

import com.alexey.minay.core_training.ExerciseId

data class TrainingExercise(
    val id: ExerciseId,
    val name: String,
    val muscleGroup: String,
    val sets: List<TrainingSet>
)