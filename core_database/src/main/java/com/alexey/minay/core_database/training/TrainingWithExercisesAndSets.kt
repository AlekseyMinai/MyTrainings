package com.alexey.minay.core_database.training

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.alexey.minay.core_database.training.entities.*

data class TrainingWithExercisesAndSets(
    @Embedded
    val training: TrainingDb,
    @Relation(parentColumn = "trainingId", entityColumn = "trainingTypeId")
    val trainingType: TrainingTypeDb,
    @Relation(
        parentColumn = "trainingTypeId",
        entityColumn = "exerciseId",
        associateBy = Junction(
            value = TrainingTypeExercisesCrossRefDb::class,
            parentColumn = "trainingTypeId",
            entityColumn = "exerciseId",
        ),
        entity = ExerciseDb::class
    )
    val exercises: List<ExerciseDb>,
    @Relation(parentColumn = "trainingId", entityColumn = "setId")
    val sets: List<ExerciseSetDb>
)