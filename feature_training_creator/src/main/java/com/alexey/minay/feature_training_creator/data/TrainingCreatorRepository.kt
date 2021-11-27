package com.alexey.minay.feature_training_creator.data

import com.alexey.minay.feature_training_creator.domain.Exercise
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository
import javax.inject.Inject

class TrainingCreatorRepository @Inject constructor() : ITrainingCreatorRepository {

    override fun getExercises(): List<Exercise> {
        TODO("Not yet implemented")
    }

}