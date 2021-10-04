package com.alexey.minay.core_database.training.entities

import androidx.room.Entity

@Entity(primaryKeys = ["trainingTypeId", "exerciseId"], tableName = "TrainingTypeExercisesCrossRef")
data class TrainingTypeExercisesCrossRefDb(
    val trainingTypeId: Long,
    val exerciseId: Long
)