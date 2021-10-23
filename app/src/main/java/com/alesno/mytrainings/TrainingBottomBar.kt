package com.alesno.mytrainings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alesno.mytrainings.navigation.Destination
import com.alexey.minay.core_ui.theme.Purple700

@Composable
fun TrainingBottomBar(
    navController: NavController,
    items: List<Destination.Home>
) {
    val currentRote = currentRote(navController = navController)
    // TODO: 23.10.2021 Подумать как сделать лучше
    if (currentRote?.startsWith("home") == false) {
        return
    }
    Column {
        Divider()

        BottomNavigation(
            elevation = 5.dp
        ) {
            items.forEach { screen ->
                val isSelected = currentRote == screen.route
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(screen.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                            }
                        }
                    },
                    label = { Text(screen.item.title) },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.item.iconRes),
                            contentDescription = screen.item.name,
                        )
                    },
                    modifier = Modifier.background(Color.White),
                    selectedContentColor = Purple700,
                    unselectedContentColor = Purple700.copy(alpha = 0.4f)
                )
            }
        }
    }
}

@Composable
private fun currentRote(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
