package com.alexey.minay.feature_training_creator.domain

import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.core_training.TrainingProgramId

data class Training(
    val title: String,
    val programId: TrainingProgramId,
    val exercises: List<ExerciseId>
)