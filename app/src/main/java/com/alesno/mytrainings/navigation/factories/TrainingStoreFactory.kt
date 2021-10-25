package com.alesno.mytrainings.navigation.factories

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.Destination
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.di.ITrainingDependencies
import com.alexey.minay.feature_training.di.TrainingComponent
import com.alexey.minay.feature_training.presentation.TrainingStore

object TrainingStoreFactory {

    @Composable
    fun create(appComponent: AppComponent, arguments: Bundle?): TrainingStore {
        val trainingComponent = remember {
            val dependencies = createTrainingDependency(appComponent, arguments)
            TrainingComponent.initAndGet(dependencies)
        }

        return viewModel(
            factory = trainingComponent.trainingStoreProvider
        )
    }

    private fun createTrainingDependency(
        appComponent: AppComponent,
        arguments: Bundle?
    ): ITrainingDependencies {
        val trainingInfoId = arguments?.getString(Destination.Training.KEY_TRAINING_INFO_ID)
        val trainingId = arguments?.getString(Destination.Training.KEY_TRAINING_ID)

        return object : ITrainingDependencies {
            override fun provideTrainingInfoId() =
                trainingInfoId?.toLongOrNull()?.let { TrainingTypeId(it) }

            override fun provideTrainingId() = trainingId?.toLongOrNull()?.let { TrainingId(it) }

            override fun provideTrainingDao() = appComponent.appDatabase.getTrainingDao()
        }
    }

}