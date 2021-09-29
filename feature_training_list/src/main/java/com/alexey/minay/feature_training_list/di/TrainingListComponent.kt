package com.alexey.minay.feature_training_list.di

import com.alexey.minay.feature_training_list.presentation.TrainingListStoreProvider
import dagger.Component

@Component(
    modules = [TrainingListModule::class, TrainingListBinding::class],
    dependencies = [TrainingListDependency::class]
)
interface TrainingListComponent {

    val trainingListStoreProvider: TrainingListStoreProvider

    companion object {

        private var mTrainingListComponent: TrainingListComponent? = null

        fun initAndGet(trainingListDependency: TrainingListDependency): TrainingListComponent =
            mTrainingListComponent ?: DaggerTrainingListComponent.builder()
                .trainingListDependency(trainingListDependency)
                .build()
                .apply {
                    mTrainingListComponent = this
                }

    }

}