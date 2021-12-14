package com.alexey.minay.feature_training_creator.di

import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorState
import dagger.Module
import dagger.Provides

@Module
class TrainingCreatorModule {

    @Provides
    fun provideTrainingCreatorState() = TrainingCreatorState.default()

}