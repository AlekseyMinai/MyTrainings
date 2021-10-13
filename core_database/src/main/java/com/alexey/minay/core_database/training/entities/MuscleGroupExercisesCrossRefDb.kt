package com.alexey.minay.core_database.training.entities

import androidx.room.Entity

@Entity(primaryKeys = ["exerciseId", "muscleGroupId"], tableName = "MuscleGroupExercisesCrossRef")
data class MuscleGroupExercisesCrossRefDb(
    val exerciseId: Long,
    val muscleGroupId: Long
)