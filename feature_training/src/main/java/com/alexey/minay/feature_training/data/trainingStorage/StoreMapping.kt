package com.alexey.minay.feature_training.data.trainingStorage

import com.alexey.minay.core_database.training.entities.ExerciseSetDb
import com.alexey.minay.core_database.training.TrainingWithExercisesAndSets
import com.alexey.minay.core_database.training.entities.ExerciseDb
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.domain.*
import java.util.*

fun TrainingWithExercisesAndSets.toDomain() = Training(
    id = TrainingId(training.trainingId),
    title = trainingType.title,
    date = Date(training.date),
    exercises = exercises.map { it.toDomain(sets) },
    trainingTypeId = TrainingTypeId(trainingType.trainingTypeId)
)

private fun ExerciseDb.toDomain(sets: List<ExerciseSetDb>) = TrainingExercise(
    id = ExerciseId(exerciseId),
    name = title,
    muscleGroup = "",
    sets = sets
        .filter { it.exerciseId == exerciseId }
        .map(ExerciseSetDb::toDomain)
)

private fun ExerciseSetDb.toDomain() = TrainingSet(
    id = TrainingSetId(setId),
    weight = weight,
    count = count
)
