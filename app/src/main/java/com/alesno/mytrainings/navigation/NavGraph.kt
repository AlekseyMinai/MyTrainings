package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.factories.TrainingHistoryStoreFactories
import com.alesno.mytrainings.navigation.factories.TrainingListStoreFactory
import com.alesno.mytrainings.navigation.factories.TrainingProgramsStoreFactory
import com.alesno.mytrainings.navigation.factories.TrainingStoreFactory
import com.alexey.minay.feature_training.di.TrainingComponent
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorComponent
import com.alexey.minay.feature_training_creator.di.ITrainingCreatorDependency
import com.alexey.minay.feature_training_creator.presentation.trainingCreator.TrainingCreatorStore
import com.alexey.minay.feature_training_creator.ui.TrainingCreator
import com.alexey.minay.feature_training_history.ui.TrainingHistory
import com.alexey.minay.feature_training_list.di.TrainingListComponent
import com.alexey.minay.feature_training_list.view.TrainingListScreen
import com.alexey.minay.feature_training_programs.view.TrainingProgramScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Destination = Destination.Home(Destination.HomeItem.PROGRAM),
    appComponent: AppComponent
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.routePart
    ) {
        navigation(
            route = startDestination.routePart,
            startDestination = startDestination.route
        ) {
            trainingHomeScreen(appComponent, navController)
            trainingProgramsScreen(appComponent, navController)
        }
        trainingListScreen(appComponent, navController)
        trainingScreen(appComponent, navController)
        composable(
            route = Destination.TrainingCreator().route
        ) {
            val component = remember {
                val dependency = object : ITrainingCreatorDependency {}
                ITrainingCreatorComponent.initAndGet(dependency)
            }

            val store = viewModel<TrainingCreatorStore>(factory = component.factory)
            TrainingCreator(
                store = store,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavGraphBuilder.trainingListScreen(
    appComponent: AppComponent,
    navController: NavHostController
) {
    composable(
        route = Destination.TrainingList().route,
    ) { backStackEntry ->
        val store = TrainingListStoreFactory.create(appComponent, backStackEntry.arguments)

        TrainingListScreen(
            store = store,
            startTraining = { trainingInfoId ->
                val route = Destination.Training(trainingTypeId = trainingInfoId).route
                navController.navigate(route)
            },
            onBackPressed = {
                navController.popBackStack()
                TrainingListComponent.release()
            }
        )
    }
}

fun NavGraphBuilder.trainingProgramsScreen(
    appComponent: AppComponent,
    navController: NavHostController
) {
    composable(
        route = Destination.Home(Destination.HomeItem.PROGRAM).route
    ) {
        val store = TrainingProgramsStoreFactory.create(appComponent)

        TrainingProgramScreen(
            store = store,
            openProgram = { trainingProgramId ->
                val route = Destination.TrainingList(trainingProgramId).route
                navController.navigate(route)
            },
            createTraining = {
                val route = Destination.TrainingCreator().route
                navController.navigate(route)
            }
        )
    }
}

fun NavGraphBuilder.trainingHomeScreen(
    appComponent: AppComponent,
    navController: NavHostController
) {
    composable(
        route = Destination.Home(Destination.HomeItem.HISTORY).route
    ) {
        val store = TrainingHistoryStoreFactories.create(appComponent)

        TrainingHistory(
            store = store,
            editTraining = { trainingId ->
                val route = Destination.Training(trainingId = trainingId).route
                navController.navigate(route)
            }
        )
    }
}

fun NavGraphBuilder.trainingScreen(
    appComponent: AppComponent,
    navController: NavHostController
) {
    composable(
        route = Destination.Training().route
    ) { backStackEntry ->
        val store = TrainingStoreFactory.create(
            appComponent = appComponent,
            arguments = backStackEntry.arguments
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