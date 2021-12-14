package com.alexey.minay.feature_training_creator.di

import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStoreFactory
import dagger.Component

@Component(
    dependencies = [ITrainingCreatorDependency::class],
    modules = [TrainingCreatorModule::class, ITrainingCreatorBinding::class]
)
interface ITrainingCreatorComponent {

    val factory: TrainingCreatorStoreFactory

    companion object {

        private var mTrainingCreatorComponent: ITrainingCreatorComponent? = null

        fun initAndGet(dependency: ITrainingCreatorDependency): ITrainingCreatorComponent {
            return mTrainingCreatorComponent ?: DaggerITrainingCreatorComponent
                .builder()
                .iTrainingCreatorDependency(dependency)
                .build()
                .apply { mTrainingCreatorComponent = this }
        }

        fun release() {
            mTrainingCreatorComponent = null
        }

    }

}