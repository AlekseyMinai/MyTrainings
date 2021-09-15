package com.alesno.mytrainings

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.alesno.mytrainings.ui.training.TrainingScreen
import com.alesno.mytrainings.ui.theme.MyTrainingsTheme
import com.alesno.mytrainings.ui.theme.Purple700
import com.alesno.mytrainings.ui.trainingList.TrainingListScreen

class MainActivity : ComponentActivity() {

    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyTrainingsTheme {
                Column {
                    TrainingListScreen(store = mViewModel.trainingListStore)
                }
            }
        }
    }
}

@Composable
fun Fab(context: Context = LocalContext.current, openScreen: () -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { openScreen() },
            backgroundColor = Purple700
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Localized description")
        }
    }) {

    }
}
