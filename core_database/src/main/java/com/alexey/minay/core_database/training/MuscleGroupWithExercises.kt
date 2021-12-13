package com.alexey.minay.core_database.training

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.alexey.minay.core_database.training.entities.*

data class MuscleGroupWithExercises (
    @Embedded
    val muscleGroup: MuscleGroupDb,
    @Relation(
        parentColumn = "muscleGroupId",
        entityColumn = "exerciseId",
        associateBy = Junction(
            value = MuscleGroupExercisesCrossRefDb::class,
            parentColumn = "muscleGroupId",
            entityColumn = "exerciseId",
        ),
        entity = ExerciseDb::class
    )
    val exercises: List<ExerciseDb>
)