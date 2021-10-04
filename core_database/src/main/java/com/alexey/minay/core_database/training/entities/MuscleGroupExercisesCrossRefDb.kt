package com.alexey.minay.core_database.training.entities

import androidx.room.Entity

@Entity(primaryKeys = ["muscleGroupId", "exerciseId"], tableName = "MuscleGroupExercisesCrossRef")
data class MuscleGroupExercisesCrossRefDb(
    val muscleGroupId: Long,
    val exerciseId: Long
)