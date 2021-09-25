package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.presentation.TrainingStoreProvider
import dagger.Component

@Component(modules = [StoreBindings::class, TrainingModule::class])
interface TrainingComponent {

    val trainingStoreProvider: TrainingStoreProvider

    companion object {

        var trainingComponent: TrainingComponent? = null

        fun initAndGet(): TrainingComponent {
            return trainingComponent ?: DaggerTrainingComponent.create()
        }

        fun release() {
            trainingComponent = null
        }

    }

}