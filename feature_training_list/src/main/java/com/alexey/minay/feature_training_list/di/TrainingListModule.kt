package com.alexey.minay.feature_training_list.di

import com.alexey.minay.feature_training_list.presentation.TrainingListState
import dagger.Module
import dagger.Provides

@Module
class TrainingListModule {

    @Provides
    fun provideTrainingListInitialState(dependency: TrainingListDependency) =
        TrainingListState.default(dependency.provideTrainingId())

}