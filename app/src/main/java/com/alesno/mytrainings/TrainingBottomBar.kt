package com.alesno.mytrainings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alesno.mytrainings.navigation.Destination
import com.alexey.minay.core_ui.R
import com.google.accompanist.insets.LocalWindowInsets

@Composable
fun TrainingBottomBar(
    navController: NavController,
    items: List<Destination.Home>
) {
    val insets = LocalWindowInsets.current
    val bottomInset = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() }

    val currentRote = currentRote(navController = navController)
    // TODO: 23.10.2021 Подумать как сделать лучше
    if (currentRote?.startsWith("home") == false) {
        return
    }
    Column(modifier = Modifier.padding(bottom = bottomInset)) {
        Divider()

        BottomNavigation(
            backgroundColor = colorResource(id = R.color.PageBackground2)
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
                    selectedContentColor = colorResource(id = R.color.PageTextColor),
                    unselectedContentColor = colorResource(id = R.color.PageTextColor).copy(alpha = 0.4f)
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
