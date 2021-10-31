package com.alexey.minay.feature_training_programs.data

import com.alexey.minay.core_database.training.entities.TrainingProgramsDb
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import com.alexey.minay.core_training.TrainingProgramId


fun TrainingProgramsDb.toDomain() = TrainingProgram(
    programId = TrainingProgramId(programId),
    title = title
)