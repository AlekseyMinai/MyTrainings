package com.alesno.mytrainings

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.alesno.mytrainings.training.TrainingScreen
import com.alesno.mytrainings.ui.theme.MyTrainingsTheme
import com.alesno.mytrainings.ui.theme.Purple700

class MainActivity : ComponentActivity() {

    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyTrainingsTheme {
                TrainingScreen(store = mViewModel.trainingStore)
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
