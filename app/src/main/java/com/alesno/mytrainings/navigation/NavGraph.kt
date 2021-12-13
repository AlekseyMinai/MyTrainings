package com.alesno.mytrainings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.factories.*
import com.alexey.minay.feature_training.di.TrainingComponent
import com.alexey.minay.feature_training.view.TrainingScreen
import com.alexey.minay.feature_training_creator.ui.TrainingCreatorScreen
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
        trainingCreatorScreen(appComponent, navController)
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

fun NavGraphBuilder.trainingCreatorScreen(
    appComponent: AppComponent,
    navController: NavHostController
) {
    composable(
        route = Destination.TrainingCreator().route
    ) {
        val store = TrainingCreatorStoreFactory.create(appComponent)

        TrainingCreatorScreen(
            store = store,
            onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}