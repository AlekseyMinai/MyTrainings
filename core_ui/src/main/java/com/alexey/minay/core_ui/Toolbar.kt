package com.alexey.minay.core_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexey.minay.core_ui.theme.Purple200

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
            Text(
                text = title,
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun Toolbar2(
    title: String,
    hasNavIcon: Boolean = false,
    onBackPressed: () -> Unit = {}
) {
    Box(
        modifier = Modifier.height(52.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = colorResource(id = R.color.white),
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 18.sp
        )
        Card(
            modifier = Modifier.size(36.dp)
                .align(Alignment.CenterEnd),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_thumbnail),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}