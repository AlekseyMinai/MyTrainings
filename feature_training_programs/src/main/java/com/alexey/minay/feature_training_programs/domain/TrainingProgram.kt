package com.alexey.minay.feature_training_programs.domain

import com.alexey.minay.core_training.TrainingProgramId

data class TrainingProgram(
    val programId: TrainingProgramId,
    val title: String
)