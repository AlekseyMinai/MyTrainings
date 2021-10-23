package com.alexey.minay.feature_training.di

import com.alexey.minay.core_database.training.ITrainingDao
import com.alexey.minay.core_training.TrainingTypeId

interface ITrainingDependencies {
    fun provideTrainingInfoId(): TrainingTypeId
    fun provideTrainingDao(): ITrainingDao
}