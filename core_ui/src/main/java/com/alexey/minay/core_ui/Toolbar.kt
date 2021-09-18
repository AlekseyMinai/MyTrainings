package com.alesno.mytrainings.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_ui.R

@Composable
fun Toolbar(
    title: String,
    hasNavIcon: Boolean = false,
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White,
        elevation = 4.dp,
        title = {
            Text(text = title, color = colorResource(id = R.color.white))
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }
        }
    )
}