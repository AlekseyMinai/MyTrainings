package com.alesno.mytrainings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.rememberNavController
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.navigation.NavGraph
import com.alexey.minay.core_ui.theme.MyTrainingsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppComponent.initNavigator(navController)
            MyTrainingsTheme {
                Column {
                    NavGraph(
                        navController = navController,
                        navigator = AppComponent.navigator
                    )
                }
            }
        }
    }
}