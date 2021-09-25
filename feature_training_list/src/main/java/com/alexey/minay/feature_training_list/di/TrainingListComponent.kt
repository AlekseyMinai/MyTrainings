package com.alexey.minay.feature_training_list.di

import com.alexey.minay.feature_training_list.presentation.TrainingListStoreProvider
import dagger.Component

@Component(modules = [TrainingListModule::class, TrainingListBinding::class])
interface TrainingListComponent {

    val trainingListStoreProvider: TrainingListStoreProvider

    companion object {

        private var mTrainingListComponent: TrainingListComponent? = null

        fun initAndGet(): TrainingListComponent =
            mTrainingListComponent ?: DaggerTrainingListComponent.create().apply {
                mTrainingListComponent = this
            }

    }

}