package com.alexey.minay.feature_training_programs.di

import com.alexey.minay.feature_training_programs.data.TrainingProgramsRepository
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsRepository
import dagger.Binds
import dagger.Module

@Module
interface ITrainingProgramsBinder {

    @Binds
    fun bindTrainingProgramsRepository(
        trainingProgramsRepository: TrainingProgramsRepository
    ): ITrainingProgramsRepository

}