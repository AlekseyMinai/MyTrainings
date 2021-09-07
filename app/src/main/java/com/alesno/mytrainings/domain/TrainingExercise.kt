package com.alesno.mytrainings.domain

data class TrainingExercise(
    val id: TrainingExerciseId,
    val info: ExerciseInfo,
    val sets: List<TrainingSet>
)