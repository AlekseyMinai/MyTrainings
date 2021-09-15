package com.alesno.mytrainings.domain.training

import java.util.*

data class Training(
    val id: TrainingId,
    val name: String,
    val date: Date,
    val exercises: List<TrainingExercise>
)