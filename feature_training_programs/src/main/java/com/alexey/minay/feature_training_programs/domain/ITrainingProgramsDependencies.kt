package com.alexey.minay.feature_training_programs.domain

import com.alexey.minay.core_database.training.ITrainingProgramsDao
import com.alexey.minay.core_utils.CoroutineDispatchersProvider

interface ITrainingProgramsDependencies {
    fun provideTrainingProgramsDao(): ITrainingProgramsDao
    fun provideCoroutineDispatcherProvider(): CoroutineDispatchersProvider
}