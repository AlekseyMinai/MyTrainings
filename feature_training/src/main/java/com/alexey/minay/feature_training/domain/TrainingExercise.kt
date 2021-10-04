package com.alexey.minay.feature_training.domain

data class TrainingExercise(
    val id: TrainingExerciseId,
    val name: String,
    val muscleGroup: String,
    val sets: List<TrainingSet>
)