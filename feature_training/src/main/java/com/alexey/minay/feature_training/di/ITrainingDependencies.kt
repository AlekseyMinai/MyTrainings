package com.alexey.minay.feature_training.di

import com.alexey.minay.core_training.TrainingInfoId

interface ITrainingDependencies {
    fun provideTrainingInfoId(): TrainingInfoId
}