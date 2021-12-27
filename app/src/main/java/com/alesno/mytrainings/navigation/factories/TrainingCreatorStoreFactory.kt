package com.alesno.mytrainings.navigation.factories

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.Destination
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorComponent
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorDependency
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore

object TrainingCreatorStoreFactory {

    @Composable
    fun create(appComponent: AppComponent, arguments: Bundle?): TrainingCreatorStore {
        val component = remember {
            val trainingProgramId = arguments?.getString(Destination.TrainingCreator.KEY_PROGRAM_ID)
                ?.toLongOrNull()
                ?: throw IllegalArgumentException("Need to pass trainingProgramId parameter")

            val dependency = object : ITrainingCreatorDependency {
                override fun provideCoroutineDispatchersProvider() =
                    appComponent.coroutineDispatchersProvider

                override fun provideExerciseGroupDao() =
                    appComponent.appDatabase.getExerciseGroupDao()

                override fun provideProgramId(): TrainingProgramId =
                    TrainingProgramId(trainingProgramId)

            }
            ITrainingCreatorComponent.initAndGet(dependency)
        }

        return viewModel(factory = component.factory)
    }

}