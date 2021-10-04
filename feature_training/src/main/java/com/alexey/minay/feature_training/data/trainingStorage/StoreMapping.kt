package com.alexey.minay.feature_training.data.trainingStorage

import com.alexey.minay.core_database.training.ExercisesWithSets
import com.alexey.minay.core_database.training.entities.ExerciseSetDb
import com.alexey.minay.core_database.training.TrainingWithExercises
import com.alexey.minay.feature_training.domain.*

fun TrainingWithExercises.toDomain() = Training(
    id = TrainingId(training.trainingId),
    name = trainingType.title,
    date = training.date,
    exercises = exercises.map { it.toDomain() }
)

private fun ExercisesWithSets.toDomain() = TrainingExercise(
    id = TrainingExerciseId(exercise.exerciseId),
    name = exercise.title,
    muscleGroup = "",
    sets = sets.map { it.toDomain() }
)

private fun ExerciseSetDb.toDomain() = TrainingSet(
    id = TrainingSetId(setId),
    weight = weight,
    count = count
)
