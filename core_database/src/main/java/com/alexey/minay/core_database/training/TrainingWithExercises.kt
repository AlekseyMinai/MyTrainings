package com.alexey.minay.core_database.training

import androidx.room.Embedded
import androidx.room.Relation
import com.alexey.minay.core_database.training.entities.TrainingDb
import com.alexey.minay.core_database.training.entities.TrainingTypeDb

data class TrainingWithExercises(
    @Embedded
    val training: TrainingDb,
    @Relation(parentColumn = "trainingId", entityColumn = "trainingTypeId")
    val trainingType: TrainingTypeDb,
    @Relation(parentColumn = "trainingTypeId", entityColumn = "exerciseId")
    val exercises: List<ExercisesWithSets>
)