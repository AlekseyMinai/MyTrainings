package com.alexey.minay.feature_training_creator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexey.minay.core_ui.BackHandler
import com.alexey.minay.core_ui.R
import com.alexey.minay.core_ui.Toolbar
import com.alexey.minay.core_ui.gradientColor
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorIntent
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorState
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore
import com.google.accompanist.insets.LocalWindowInsets

@Composable
fun TrainingCreatorScreen(
    store: TrainingCreatorStore,
    onBackPressed: () -> Unit
) {
    val state by store.state.collectAsState()
    val selectedExercises  = state.items.mapNotNull { item ->
            when(item) {
                is TrainingCreatorState.MuscleGroupState -> null
                is TrainingCreatorState.ExerciseState -> when(item.isSelected) {
                    true -> item
                    false -> null
                }
            }
        }

    TrainingCreator(onBackPressed = onBackPressed,
        openSelectTrainingScreen = {
            store.accept(TrainingCreatorIntent.OpenExerciseSelectorScreen)
        },
        selectedExercises = selectedExercises
    )
}

@Composable
fun TrainingCreator(
    onBackPressed: () -> Unit,
    openSelectTrainingScreen: () -> Unit,
    selectedExercises: List<TrainingCreatorState.ExerciseState>
) {
    BackHandler(onBack = onBackPressed)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColor())
    ) {
        val lazyListState = rememberLazyListState()
        val firstItemHeight = 200.dp
        val title = "Новая тренировка"

        val insets = LocalWindowInsets.current
        val bottomInset = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() }

        TrainingExercises(
            lazyListState = lazyListState,
            firstItemHeight = firstItemHeight,
            title = title,
            selectedExercises = selectedExercises
        )

        Toolbar(
            title = title,
            lazyListState = lazyListState,
            backgroundColor = colorResource(id = R.color.PageBackground2),
            firstItemHeight = firstItemHeight,
            onBackPressed = onBackPressed
        )

        FloatingActionButton(
            onClick = { openSelectTrainingScreen() },
            backgroundColor = colorResource(id = R.color.purple_200),
            contentColor = colorResource(R.color.CardContent),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp + bottomInset + 68.dp)
        ) {
            Icon(Icons.Filled.Add, "")
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp + bottomInset)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Создать",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun TrainingExercises(
    title: String,
    lazyListState: LazyListState,
    firstItemHeight: Dp,
    selectedExercises: List<TrainingCreatorState.ExerciseState>
) {
    val firstItemHeightPx =
        with(LocalDensity.current) { firstItemHeight.roundToPx().toFloat() }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_thumbnail),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(firstItemHeight)
                        .fillMaxSize()
                )

                Text(
                    text = title,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .graphicsLayer {
                            alpha = when (lazyListState.firstVisibleItemIndex) {
                                0 -> 1 - ((lazyListState.firstVisibleItemScrollOffset) / (firstItemHeightPx))
                                    .coerceIn(0f, 1f)
                                else -> 0f
                            }
                        }
                        .align(Alignment.BottomStart),
                    color = colorResource(id = R.color.PageTextColor)
                )
            }
        }
        item {
            TrainingName()
        }

        items(selectedExercises.size) { index ->
            TrainingExercise(selectedExercises[index])
        }
    }
}

@Composable
private fun TrainingName() {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.CardBackground),
        elevation = 0.dp
    ) {
        OutlinedTextField(
            value = ("").toString(),
            onValueChange = { },
            label = { Text("Введите название тренировки") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.CardContent),
                unfocusedBorderColor = colorResource(id = R.color.CardContent),
                focusedLabelColor = colorResource(id = R.color.CardContent),
                unfocusedLabelColor = colorResource(id = R.color.CardContent),
                textColor = colorResource(id = R.color.CardContent),
                cursorColor = colorResource(id = R.color.CardContent),
            ),
            modifier = Modifier
                .padding(
                    vertical = 12.dp,
                    horizontal = 8.dp
                )
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun TrainingCreatorScreenPreview() {
    TrainingCreator(
        onBackPressed = { /*TODO*/ },
        openSelectTrainingScreen = {},
        emptyList()
    )
}