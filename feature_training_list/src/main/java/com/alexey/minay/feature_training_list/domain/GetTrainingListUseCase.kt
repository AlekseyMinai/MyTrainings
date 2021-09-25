package com.alexey.minay.feature_training_list.domain

import com.alexey.minay.feature_training_list.fake.FakeData

class GetTrainingListUseCase {

    operator fun invoke(): List<TrainingInfo> {
        return FakeData.getTrainings()
    }

}