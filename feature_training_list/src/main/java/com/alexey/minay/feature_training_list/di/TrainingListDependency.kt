package com.alexey.minay.feature_training_list.di

import com.alexey.minay.core_database.training.TrainingListDao
import com.alexey.minay.core_utils.CoroutineDispatchersProvider

interface TrainingListDependency {
    fun provideTrainingListDao(): TrainingListDao
    fun provideCoroutineDispatchersProvider(): CoroutineDispatchersProvider
}