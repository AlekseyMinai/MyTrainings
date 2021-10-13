package com.alexey.minay.core_database.training.entities

import androidx.room.Entity

@Entity(
    tableName = "TrainingTypesExercisesCrossRef",
    primaryKeys = ["exerciseId", "trainingTypeId"]
)
data class TrainingTypeExercisesCrossRefDb(
    val exerciseId: Long,
    val trainingTypeId: Long
)