package com.alesno.mytrainings.navigation.factories

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.Destination
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.feature_training_list.di.TrainingListComponent
import com.alexey.minay.feature_training_list.di.TrainingListDependency
import com.alexey.minay.feature_training_list.presentation.TrainingListStore

object TrainingListStoreFactory {

    @Composable
    fun create(appComponent: AppComponent, arguments: Bundle?): TrainingListStore {
        val trainingListComponent = remember {
            val trainingListDependency = createTrainingListDependencies(appComponent, arguments)
            TrainingListComponent.initAndGet(trainingListDependency)
        }

        return viewModel(
            factory = trainingListComponent.trainingListStoreFactory
        )
    }

    private fun createTrainingListDependencies(appComponent: AppComponent, arguments: Bundle?) =
        object : TrainingListDependency {
            override fun provideTrainingListDao() =
                appComponent.appDatabase.getTrainingListDao()

            override fun provideCoroutineDispatchersProvider() =
                appComponent.coroutineDispatchersProvider

            override fun provideTrainingId(): TrainingProgramId? {
                val programId = arguments?.getString(Destination.TrainingList.KEY_PROGRAM_ID)
                return programId?.toLongOrNull()?.let { TrainingProgramId(it) }
            }

        }

}