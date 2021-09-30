package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alesno.mytrainings.di.AppComponent
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.feature_training.di.ITrainingDependencies
import com.alexey.minay.feature_training.di.TrainingComponent
import com.alexey.minay.feature_training.presentation.TrainingStore
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_list.di.TrainingListComponent
import com.alexey.minay.feature_training_list.di.TrainingListDependency
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.alexey.minay.feature_training_list.view.TrainingListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination = Destination.TrainingList,
    appComponent: AppComponent
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(
            route = Destination.TrainingList.route,
        ) {
            val trainingListComponent = remember {
                val trainingListDependency = object : TrainingListDependency {
                    override fun provideTrainingListDao() =
                        appComponent.appDatabase.getTrainingListDao()

                    override fun provideCoroutineDispatchersProvider() =
                        appComponent.coroutineDispatchersProvider

                }
                TrainingListComponent.initAndGet(trainingListDependency)
            }
            val store = viewModel<TrainingListStore>(
                factory = trainingListComponent.trainingListStoreProvider
            )
            TrainingListScreen(
                store = store,
                startTraining = { trainingInfoId ->
                    val route = Destination.Training(trainingTypeId = trainingInfoId).route
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = Destination.Training().route
        ) {
            val trainingComponent = remember {
                val trainingInfoId =
                    it.arguments?.getLong(Destination.Training.KEY_TRAINING_INFO_ID)!!
                val trainingDependencies = object : ITrainingDependencies {
                    override fun provideTrainingInfoId() = TrainingTypeId(trainingInfoId)
                }
                TrainingComponent.initAndGet(trainingDependencies)
            }
            val store = viewModel<TrainingStore>(
                factory = trainingComponent.trainingStoreProvider
            )
            TrainingScreen(
                store = store,
                onBackPressed = {
                    navController.popBackStack()
                    TrainingComponent.release()
                }
            )
        }
    }
}