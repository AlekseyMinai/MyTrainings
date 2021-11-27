package com.alexey.minay.core_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource

@Composable
fun gradientColor() = Brush.verticalGradient(
    colors = listOf(
        colorResource(id = R.color.PageBackground1),
        colorResource(id = R.color.PageBackground2),
    )
)