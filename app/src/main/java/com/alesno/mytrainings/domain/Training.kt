package com.alesno.mytrainings.domain

import java.util.*

data class Training(
    val id: TrainingId,
    val name: String,
    val date: Date,
    val exercises: List<TrainingExercise>
)