package com.alesno.mytrainings.navigation.factories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alexey.minay.core_database.training.ITrainingHistoryDao
import com.alexey.minay.feature_training_history.di.ITrainingHistoryDependencies
import com.alexey.minay.feature_training_history.di.TrainingHistoryComponent
import com.alexey.minay.feature_training_history.presentation.TrainingHistoryStore

object TrainingHistoryStoreFactories {

    @Composable
    fun create(appComponent: AppComponent): TrainingHistoryStore {
        val trainingHistoryComponent = remember {
            val dependencies = createTrainingHistoryDependencies(appComponent)
            TrainingHistoryComponent.initAndGet(dependencies)
        }

        return viewModel(
            factory = trainingHistoryComponent.trainingHistoryStoreFactory
        )
    }

    private fun createTrainingHistoryDependencies(appComponent: AppComponent) =
        object : ITrainingHistoryDependencies {
            override fun provideTrainingHistoryDao(): ITrainingHistoryDao {
                return appComponent.appDatabase.getTrainingHistoryDao()
            }
        }

}