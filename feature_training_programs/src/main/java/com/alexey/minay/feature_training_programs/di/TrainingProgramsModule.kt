package com.alexey.minay.feature_training_programs.di

import com.alexey.minay.feature_training_programs.presentation.TrainingProgramsState
import dagger.Module
import dagger.Provides

@Module
class TrainingProgramsModule {

    @Provides
    fun provideTrainingProgramStoreInitialState() = TrainingProgramsState.default()

}