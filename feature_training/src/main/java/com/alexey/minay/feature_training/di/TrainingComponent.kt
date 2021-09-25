package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.presentation.TrainingStoreProvider
import dagger.Component

@Component(
    modules = [StoreBindings::class, TrainingModule::class],
    dependencies = [ITrainingDependencies::class]
)
interface TrainingComponent {

    val trainingStoreProvider: TrainingStoreProvider

    companion object {

        private var mTrainingComponent: TrainingComponent? = null

        fun initAndGet(trainingDependencies: ITrainingDependencies): TrainingComponent {
            return mTrainingComponent ?: DaggerTrainingComponent
                .builder()
                .iTrainingDependencies(trainingDependencies)
                .build()
                .apply { mTrainingComponent = this }
        }

        fun release() {
            mTrainingComponent = null
        }

    }

}