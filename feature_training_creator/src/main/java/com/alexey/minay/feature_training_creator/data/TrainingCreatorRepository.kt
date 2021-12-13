package com.alexey.minay.feature_training_creator.data

import com.alexey.minay.core_database.training.IExerciseGroupDao
import com.alexey.minay.core_database.training.MuscleGroupWithExercises
import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.core_utils.CoroutineDispatchersProvider
import com.alexey.minay.feature_training_creator.domain.Exercise
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository
import com.alexey.minay.feature_training_creator.domain.MuscleGroup
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingCreatorRepository @Inject constructor(
    private val storage: IExerciseGroupDao,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : ITrainingCreatorRepository {

    override suspend fun getExercises() = withContext(coroutineDispatchersProvider.io) {
        storage.getExerciseGroup().asDomain()
    }

    private fun List<MuscleGroupWithExercises>.asDomain() = map {
        MuscleGroup(
            muscleGroupId = MuscleGroupId(it.muscleGroup.muscleGroupId),
            title = it.muscleGroup.title,
            exercises = it.exercises.map { exercise ->
                Exercise(
                    id = ExerciseId(exercise.exerciseId),
                    title = exercise.title
                )
            }
        )
    }

}