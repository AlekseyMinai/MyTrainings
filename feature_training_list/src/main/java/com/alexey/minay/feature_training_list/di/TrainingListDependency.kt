package com.alexey.minay.feature_training_list.di

import com.alexey.minay.core_database.training.ITrainingListDao
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.core_utils.CoroutineDispatchersProvider

interface TrainingListDependency {
    fun provideTrainingListDao(): ITrainingListDao
    fun provideCoroutineDispatchersProvider(): CoroutineDispatchersProvider
    fun provideTrainingId(): TrainingProgramId
}