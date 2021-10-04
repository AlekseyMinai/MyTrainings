package com.alexey.minay.core_database.training

import androidx.room.Embedded
import androidx.room.Relation
import com.alexey.minay.core_database.training.entities.ExerciseSetDb
import com.alexey.minay.core_database.training.entities.ExerciseDb

data class ExercisesWithSets(
    @Embedded
    val exercise: ExerciseDb,
    @Relation(parentColumn = "exerciseId", entityColumn = "setId")
    val sets: List<ExerciseSetDb>
)