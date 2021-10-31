package com.alesno.mytrainings.navigation.factories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alesno.mytrainings.di.AppComponent
import com.alexey.minay.core_database.training.ITrainingProgramsDao
import com.alexey.minay.feature_training_programs.di.ITrainingProgramsComponent
import com.alexey.minay.feature_training_programs.domain.ITrainingProgramsDependencies
import com.alexey.minay.feature_training_programs.presentation.TrainingProgramsStore

object TrainingProgramsStoreFactory {

    @Composable
    fun create(appComponent: AppComponent): TrainingProgramsStore {
        val trainingProgramsComponent = remember {
            val trainingListDependency = createTrainingListDependencies(appComponent)
            ITrainingProgramsComponent.initAndGet(trainingListDependency)
        }

        return viewModel(
            factory = trainingProgramsComponent.trainingProgramStoreFactory
        )
    }

    private fun createTrainingListDependencies(appComponent: AppComponent) =
        object : ITrainingProgramsDependencies {
            override fun provideTrainingProgramsDao(): ITrainingProgramsDao {
                return appComponent.appDatabase.getTrainingProgramsDao()
            }
        }

}