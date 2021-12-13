package com.alexey.minay.core_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets

@Composable
fun Toolbar(
    title: String,
    onBackPressed: () -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    lazyListState: LazyListState,
    firstItemHeight: Dp
) {
    val insets = LocalWindowInsets.current
    val topInset = with(LocalDensity.current) { insets.statusBars.top.toDp() }
    val toolbarHeight = 52.dp

    val firstItemHeightPx = with(LocalDensity.current) { firstItemHeight.roundToPx().toFloat() }
    val topInsetPx = with(LocalDensity.current) { topInset.roundToPx().toFloat() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    alpha = when (lazyListState.firstVisibleItemIndex) {
                        0 -> ((lazyListState.firstVisibleItemScrollOffset) /
                                (firstItemHeightPx - topInsetPx - toolbarHeightPx))
                            .coerceIn(0f, 1f)
                        else -> 1f
                    }
                }
                .height(toolbarHeight + topInset)
                .background(backgroundColor)
                .padding(start = 44.dp, end = 16.dp)
        ) {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = topInset),
                fontSize = 20.sp
            )
        }
        IconButton(
            onClick = { onBackPressed() },
            modifier = Modifier
                .padding(top = topInset)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "",
                tint = colorResource(R.color.ToolbarContent)
            )
        }
    }
}

@Composable
fun Toolbar2(
    title: String,
    hasNavIcon: Boolean = false,
    onBackPressed: () -> Unit = {},
    backgroundColor: Color = Color.Transparent
) {
    val insets = LocalWindowInsets.current
    val topInset = with(LocalDensity.current) { insets.statusBars.top.toDp() }

    Box(
        modifier = Modifier
            .height(52.dp + topInset)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = topInset)
            .background(backgroundColor)
    ) {
        Text(
            text = title,
            color = colorResource(id = R.color.white),
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 20.sp
        )
        Card(
            modifier = Modifier
                .size(36.dp)
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


@Composable
fun Toolbar3(
    title: String,
    hasNavIcon: Boolean = false,
    onBackPressed: () -> Unit = {},
    backgroundColor: Color = Color.Transparent
) {
    val insets = LocalWindowInsets.current
    val topInset = with(LocalDensity.current) { insets.statusBars.top.toDp() }

    Box(
        modifier = Modifier
            .height(52.dp + topInset)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = topInset)
            .background(backgroundColor)
    ) {
        Text(
            text = title,
            color = colorResource(id = R.color.white),
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 20.sp
        )
    }
}