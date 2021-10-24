package com.alesno.mytrainings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.alesno.mytrainings.navigation.Destination
import com.alesno.mytrainings.navigation.NavGraph
import com.alexey.minay.core_ui.theme.MyTrainingsTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val bottomNavigationItems = mutableListOf(
                Destination.Home(Destination.HomeItem.TRAINING_LIST),
                Destination.Home(Destination.HomeItem.HISTORY)
            )

            MyTrainingsTheme(window = window) {
                ProvideWindowInsets {
                    Scaffold(bottomBar = {
                        TrainingBottomBar(
                            navController = navController,
                            items = bottomNavigationItems
                        )
                    }) {
                        NavGraph(
                            navController = navController,
                            appComponent = appComponent
                        )
                    }
                }
            }
        }
    }
}