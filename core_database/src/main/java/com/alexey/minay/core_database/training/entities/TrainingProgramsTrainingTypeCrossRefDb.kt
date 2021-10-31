package com.alexey.minay.core_database.training.entities

import androidx.room.Entity

@Entity(
    tableName = "TrainingProgramTrainingTypeCrossRef",
    primaryKeys = ["programId", "trainingTypeId"]
)
data class TrainingProgramsTrainingTypeCrossRefDb(
    val programId: Long,
    val trainingTypeId: Long
)