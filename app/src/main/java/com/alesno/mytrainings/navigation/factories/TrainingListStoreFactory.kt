package com.alesno.mytrainings.navigation.factories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alexey.minay.feature_training_list.di.TrainingListComponent
import com.alexey.minay.feature_training_list.di.TrainingListDependency
import com.alexey.minay.feature_training_list.presentation.TrainingListStore

object TrainingListStoreFactory {

    @Composable
    fun create(appComponent: AppComponent): TrainingListStore {
        val trainingListComponent = remember {
            val trainingListDependency = createTrainingListDependencies(appComponent)
            TrainingListComponent.initAndGet(trainingListDependency)
        }

        return viewModel(
            factory = trainingListComponent.trainingListStoreFactory
        )
    }

    private fun createTrainingListDependencies(appComponent: AppComponent) =
        object : TrainingListDependency {
            override fun provideTrainingListDao() =
                appComponent.appDatabase.getTrainingListDao()

            override fun provideCoroutineDispatchersProvider() =
                appComponent.coroutineDispatchersProvider

        }

}