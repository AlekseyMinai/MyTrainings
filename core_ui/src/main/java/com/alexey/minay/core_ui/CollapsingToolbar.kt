package com.alexey.minay.core_ui

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import kotlin.math.roundToInt

@Composable
fun CollapsingToolbar(
    @DrawableRes backgroundImage: Int,
    onBackPressed: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    var scrolledY = 0f
    var previousOffset = 0

    val insets = LocalWindowInsets.current
    val topInset = with(LocalDensity.current) { insets.statusBars.top.toDp() }

    val toolbarExpandHeight = 200.dp + topInset
    val toolbarCollapseHeight = 56.dp + topInset
    val toolbarExpandHeightPx =
        with(LocalDensity.current) { toolbarExpandHeight.roundToPx().toFloat() }
    val toolbarCollapseHeightPx =
        with(LocalDensity.current) { toolbarCollapseHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val firstItem = remember { mutableStateOf(toolbarExpandHeightPx) }
    val lazyListState = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (lazyListState.firstVisibleItemIndex == 0) {
                    val delta = available.y
                    val newOffset = toolbarOffsetHeightPx.value + delta / 2
                    if (lazyListState.layoutInfo.visibleItemsInfo.size == lazyListState.layoutInfo.totalItemsCount) {
                        toolbarOffsetHeightPx.value =
                            newOffset.coerceIn(-toolbarExpandHeightPx + toolbarCollapseHeightPx, 0f)
                        firstItem.value = toolbarExpandHeightPx + toolbarOffsetHeightPx.value
                    } else {
                        firstItem.value = toolbarExpandHeightPx
                    }
                }
                return Offset.Zero
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            state = lazyListState, modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(with(LocalDensity.current) { firstItem.value.toDp() })
                )
            }
            content()
        }
        Box(
            modifier = Modifier
                .height(toolbarExpandHeight)
                .offset {
                    if (lazyListState.layoutInfo.visibleItemsInfo.size == lazyListState.layoutInfo.totalItemsCount + 1000) {
                        val maxOffset = toolbarExpandHeightPx - toolbarCollapseHeightPx
/*                        val offset1 = when (lazyListState.firstVisibleItemIndex) {
                            0 -> lazyListState.firstVisibleItemScrollOffset
                                .toFloat()
                                .coerceIn(0f, maxOffset)
                            else -> maxOffset
                        }*/
                        IntOffset(x = 0, y = (toolbarOffsetHeightPx.value).roundToInt())
                    } else {
                        val maxOffset = toolbarExpandHeightPx - toolbarCollapseHeightPx
                        val offset = when (lazyListState.firstVisibleItemIndex) {
                            0 -> lazyListState.firstVisibleItemScrollOffset
                                .toFloat()
                                .coerceIn(0f, maxOffset)
                            else -> maxOffset
                        }
                        IntOffset(x = 0, y = -offset.roundToInt())
                    }
                }) {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .graphicsLayer {
//                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
//                        translationY = scrolledY * 0.5f
//                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
                    .height(toolbarExpandHeight)
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
            )
            Button(onClick = { onBackPressed() }, modifier = Modifier.padding(top = topInset)) {

            }
        }
    }
}