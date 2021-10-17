package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.presentation.TrainingState
import dagger.Module
import dagger.Provides

@Module
class TrainingModule {

    @Provides
    fun provideTrainingInitState(dependencies: ITrainingDependencies): TrainingState {
        return TrainingState.default(dependencies.provideTrainingInfoId())
    }

}