package com.alexey.minay.feature_training.di

import com.alexey.minay.feature_training.presentation.TrainingStoreFactory
import dagger.Component

@Component(
    modules = [StoreBindings::class, TrainingModule::class, TrainingBinding::class],
    dependencies = [ITrainingDependencies::class]
)
interface TrainingComponent {

    val trainingStoreFactory: TrainingStoreFactory

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