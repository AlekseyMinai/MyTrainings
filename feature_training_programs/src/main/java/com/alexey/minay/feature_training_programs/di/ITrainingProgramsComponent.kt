package com.alexey.minay.feature_training_programs.di

import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsDependencies
import com.alexey.minay.feature_training_programs.presentation.TrainingProgramStoreFactory
import dagger.Component

@Component(
    modules = [TrainingProgramsModule::class, ITrainingProgramsBinder::class],
    dependencies = [ITrainingProgramsDependencies::class]
)
interface ITrainingProgramsComponent {

    val trainingProgramStoreFactory: TrainingProgramStoreFactory

    companion object {

        private var mComponent: ITrainingProgramsComponent? = null

        fun initAndGet(dependencies: ITrainingProgramsDependencies): ITrainingProgramsComponent {
            return mComponent ?: DaggerITrainingProgramsComponent.builder()
                    .iTrainingProgramsDependencies(dependencies)
                    .build()
                    .apply { mComponent = this }
        }

        fun release() {
            mComponent = null
        }

    }

}