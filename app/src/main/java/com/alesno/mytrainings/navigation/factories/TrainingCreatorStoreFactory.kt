package com.alesno.mytrainings.navigation.factories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorComponent
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorDependency
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore

object TrainingCreatorStoreFactory {

    @Composable
    fun create(appComponent: AppComponent): TrainingCreatorStore {
        val component = remember {
            val dependency = object : ITrainingCreatorDependency {
                override fun provideCoroutineDispatchersProvider() =
                    appComponent.coroutineDispatchersProvider

                override fun provideExerciseGroupDao() =
                    appComponent.appDatabase.getExerciseGroupDao()
            }
            ITrainingCreatorComponent.initAndGet(dependency)
        }

        return viewModel(factory = component.factory)
    }

}