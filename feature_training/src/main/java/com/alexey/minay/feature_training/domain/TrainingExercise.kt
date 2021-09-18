package com.alexey.minay.feature_training.domain

data class TrainingExercise(
    val id: TrainingExerciseId,
    val info: ExerciseInfo,
    val sets: List<TrainingSet>
)