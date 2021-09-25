package com.alesno.mytrainings.di

import com.alexey.minay.feature_training.presentation.TrainingState
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideTrainingInitState(): TrainingState {
        return TrainingState.default()
    }

}