package com.alexey.minay.feature_training_history.di

import com.alexey.minay.feature_training_history.presentation.TrainingHistoryState
import dagger.Module
import dagger.Provides

@Module
class TrainingHistoryModule {

    @Provides
    fun provideTrainingListInitialState() =
        TrainingHistoryState.default()

}