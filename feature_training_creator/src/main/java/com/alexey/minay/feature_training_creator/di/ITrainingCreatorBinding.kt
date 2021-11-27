package com.alexey.minay.feature_training_creator.di

import com.alexey.minay.feature_training_creator.data.TrainingCreatorRepository
import com.alexey.minay.feature_training_creator.domain.ITrainingCreatorRepository
import dagger.Binds
import dagger.Module

@Module
interface ITrainingCreatorBinding {

    @Binds
    fun bindTrainingCreatorRepo(
        trainingCreatorRepository: TrainingCreatorRepository
    ): ITrainingCreatorRepository

}