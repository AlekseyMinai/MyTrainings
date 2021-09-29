package com.alexey.minay.feature_training_list.data

import com.alexey.minay.core_database.training_list.TrainingListDao
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import com.alexey.minay.feature_training_list.domain.TrainingType
import com.alexey.minay.feature_training_list.fake.FakeData
import javax.inject.Inject

class TrainingListRepository @Inject constructor(
    private val trainingListDao: TrainingListDao
) : ITrainingListRepository {
    override fun getTrainingList(): List<TrainingType> {
        return FakeData.getTrainings()
    }
}