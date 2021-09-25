package com.alexey.minay.feature_training_list.data

import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import com.alexey.minay.feature_training_list.domain.TrainingInfo
import com.alexey.minay.feature_training_list.fake.FakeData
import javax.inject.Inject

class TrainingListRepository @Inject constructor() : ITrainingListRepository {
    override fun getTrainingList(): List<TrainingInfo> {
        return FakeData.getTrainings()
    }
}