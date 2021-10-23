package com.alexey.minay.feature_training_history.di

import com.alexey.minay.feature_training_history.data.TrainingHistoryRepository
import com.alexey.minay.feature_training_history.domain.ITrainingHistoryRepository
import dagger.Binds
import dagger.Module

@Module
interface ITrainingHistoryBindModule {

    @Binds
    fun bindTrainingHistoryRepository(
        trainingHistoryRepository: TrainingHistoryRepository
    ): ITrainingHistoryRepository

}