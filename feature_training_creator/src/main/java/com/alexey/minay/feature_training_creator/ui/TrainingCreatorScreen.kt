package com.alexey.minay.feature_training_creator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.alexey.minay.core_ui.theme.Purple200
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorAction
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorState
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore
import com.google.accompanist.insets.LocalWindowInsets

@Composable
fun TrainingCreatorScreen(
    store: TrainingCreatorStore,
    onBackPressed: () -> Unit
) {
    val state by store.state.collectAsState()
    val selectedExercises = state.items.mapNotNull { item ->
        when (item) {
            is TrainingCreatorState.MuscleGroupState -> null
            is TrainingCreatorState.ExerciseState -> when (item.isSelected) {
                true -> item
                false -> null
            }
        }
    }

    TrainingCreator(
        onBackPressed = onBackPressed,
        selectedExercises = selectedExercises,
        trainingTitle = state.title,
        onTrainingTitleChanged = {
            store.accept(TrainingCreatorAction.UpdateTrainingTitle(it))
        },
        openSelectTrainingScreen = {
            store.accept(TrainingCreatorAction.OpenExerciseSelectorScreen)
        },
        onSaveTraining = {
            store.accept(TrainingCreatorAction.SaveTraining)
        }
    )
}

@Composable
fun TrainingCreator(
    onBackPressed: () -> Unit,
    openSelectTrainingScreen: () -> Unit,
    selectedExercises: List<TrainingCreatorState.ExerciseState>,
    trainingTitle: String,
    onTrainingTitleChanged: (String) -> Unit,
    onSaveTraining: () -> Unit
) {
    val insets = LocalWindowInsets.current
    val bottomInset = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() }
    val rightInset = with(LocalDensity.current) { insets.navigationBars.right.toDp() }
    val leftInset = with(LocalDensity.current) { insets.navigationBars.left.toDp() }

    BackHandler(onBack = onBackPressed)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColor())
            .padding(
                bottom = bottomInset,
                start = rightInset,
                end = leftInset
            )
    ) {
        val lazyListState = rememberLazyListState()
        val firstItemHeight = 220.dp
        val title = stringResource(R.string.new_training)

        TrainingExercises(
            title = title,
            lazyListState = lazyListState,
            firstItemHeight = firstItemHeight,
            selectedExercises = selectedExercises,
            onTrainingTitleChanged = onTrainingTitleChanged,
            trainingTitle = trainingTitle
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
                .padding(
                    end = dimensionResource(R.dimen.page_horizontal_padding),
                    bottom = 74.dp
                )
        ) {
            Icon(Icons.Filled.Add, "")
        }

        Button(
            onClick = onSaveTraining,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .padding(horizontal = dimensionResource(R.dimen.page_horizontal_padding))
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
    selectedExercises: List<TrainingCreatorState.ExerciseState>,
    trainingTitle: String,
    onTrainingTitleChanged: (String) -> Unit
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ToolbarItem(firstItemHeight, title, lazyListState)
        }

        item {
            TrainingName(
                title = trainingTitle,
                onTrainingTitleChanged
            )
        }

        items(selectedExercises.size) { index ->
            TrainingExercise(selectedExercises[index], index == selectedExercises.lastIndex)
        }
    }
}

@Composable
fun ToolbarItem(
    firstItemHeight: Dp,
    title: String,
    lazyListState: LazyListState
) {
    val firstItemHeightPx =
        with(LocalDensity.current) { firstItemHeight.roundToPx().toFloat() }

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
                .padding(
                    start = dimensionResource(R.dimen.page_horizontal_padding),
                    top = 16.dp,
                    bottom = 16.dp
                )
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

@Composable
fun TrainingExercise(
    exerciseState: TrainingCreatorState.ExerciseState,
    isLast: Boolean
) {
    val bottomPadding = when {
        isLast -> 74.dp
        else -> 4.dp
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp, bottom = bottomPadding)
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.CardBackground),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Card(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                elevation = 2.dp,
                backgroundColor = Purple200
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gym_dumbbell),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }
            Text(
                text = exerciseState.exercise.title,
                color = colorResource(id = R.color.PageTextColor),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun TrainingName(
    title: String,
    onTitleChange: (String) -> Unit
) {
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
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(stringResource(id = R.string.input_training_title)) },
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
//    TrainingCreator(
//        onBackPressed = { /*TODO*/ },
//        openSelectTrainingScreen = {},
//        emptyList()
//    )
}