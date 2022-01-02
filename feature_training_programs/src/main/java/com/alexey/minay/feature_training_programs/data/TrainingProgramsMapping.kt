package com.alexey.minay.feature_training_programs.data

import com.alexey.minay.core_database.training.entities.TrainingProgramDb
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import com.alexey.minay.core_training.TrainingProgramId


fun TrainingProgramDb.toDomain() = TrainingProgram(
    programId = TrainingProgramId(programId),
    title = title
)