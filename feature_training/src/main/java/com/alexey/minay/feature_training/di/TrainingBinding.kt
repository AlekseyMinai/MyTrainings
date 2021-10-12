package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.data.ITrainingStorage
import com.alexey.minay.feature_training.data.TrainingRepository
import com.alexey.minay.feature_training.data.trainingStorage.TrainingStorage
import com.alexey.minay.feature_training.domain.ITrainingRepository
import dagger.Binds
import dagger.Module

@Module
interface TrainingBinding {

    @Binds
    fun bindTrainingRepository(trainingRepository: TrainingRepository): ITrainingRepository

    @Binds
    fun bindTrainingStorage(trainingStorage: TrainingStorage): ITrainingStorage

}