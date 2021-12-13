package com.alexey.minay.feature_training_creator.di

import com.alexey.minay.core_database.training.IExerciseGroupDao
import com.alexey.minay.core_utils.CoroutineDispatchersProvider

interface ITrainingCreatorDependency {
    fun provideCoroutineDispatchersProvider(): CoroutineDispatchersProvider
    fun provideExerciseGroupDao(): IExerciseGroupDao
}