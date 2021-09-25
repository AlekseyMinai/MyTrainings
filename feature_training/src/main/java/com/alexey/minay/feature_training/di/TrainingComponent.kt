package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.presentation.TrainingStoreProvider
import dagger.Component

@Component(modules = [StoreBindings::class, TrainingModule::class])
interface TrainingComponent {

    val trainingStoreProvider: TrainingStoreProvider

    companion object {

        private var mTrainingComponent: TrainingComponent? = null

        fun initAndGet(): TrainingComponent {
            return mTrainingComponent ?: DaggerTrainingComponent.create().apply {
                mTrainingComponent = this
            }
        }

        fun release() {
            mTrainingComponent = null
        }

    }

}