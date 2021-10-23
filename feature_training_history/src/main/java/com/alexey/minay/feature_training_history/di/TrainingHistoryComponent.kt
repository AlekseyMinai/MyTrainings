package com.alexey.minay.feature_training_history.di

import dagger.Component

@Component(
    modules = [TrainingHistoryModule::class, ITrainingHistoryBindModule::class],
    dependencies = [ITrainingHistoryDependencies::class]
)
interface TrainingHistoryComponent {

    val trainingHistoryStoreFactory: TrainingHistoryStoreFactory

    companion object {

        private var mTrainingListComponent: TrainingHistoryComponent? = null

        fun initAndGet(dependencies: ITrainingHistoryDependencies): TrainingHistoryComponent =
            mTrainingListComponent ?: DaggerTrainingHistoryComponent.builder()
                .iTrainingHistoryDependencies(dependencies)
                .build()
                .apply {
                    mTrainingListComponent = this
                }

    }

}