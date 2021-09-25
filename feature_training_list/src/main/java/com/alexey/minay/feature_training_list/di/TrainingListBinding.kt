package com.alexey.minay.feature_training_list.di

import com.alexey.minay.feature_training_list.data.TrainingListRepository
import com.alexey.minay.feature_training_list.domain.ITrainingListRepository
import dagger.Binds
import dagger.Module

@Module
interface TrainingListBinding {

    @Binds
    fun bindTrainingListRepository(trainingListRepository: TrainingListRepository): ITrainingListRepository

}