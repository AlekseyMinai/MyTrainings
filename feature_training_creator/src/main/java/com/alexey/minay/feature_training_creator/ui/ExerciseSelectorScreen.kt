package com.alexey.minay.feature_training_creator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexey.minay.core_training.ExerciseId
import com.alexey.minay.core_ui.BackHandler
import com.alexey.minay.core_ui.Toolbar3
import com.alexey.minay.core_ui.gradientColor
import com.alexey.minay.core_ui.theme.Purple200
import com.alexey.minay.feature_training_creator.domain.MuscleGroupId
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorAction
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorState
import com.alexey.minay.feature_training_creator.presentation.TrainingCreatorStore
import com.google.accompanist.insets.LocalWindowInsets
import com.alexey.minay.core_ui.R as CoreUiR

@Composable
fun ExerciseSelectorScreen(store: TrainingCreatorStore) {
    val state by store.state.collectAsState()

    ExerciseSelector(
        onBackPressed = { store.accept(TrainingCreatorAction.OpenTrainingCreatorScreen) },
        items = state.items,
        onExerciseSelected = { store.accept(TrainingCreatorAction.ChangeExerciseSelection(it)) },
        onChangeExpandState = { store.accept(TrainingCreatorAction.ChangeExpandState(it)) },
    )
}

@Composable
fun ExerciseSelector(
    onBackPressed: () -> Unit,
    items: List<TrainingCreatorState.Item>,
    onExerciseSelected: (ExerciseId) -> Unit,
    onChangeExpandState: (MuscleGroupId) -> Unit
) {
    BackHandler(onBack = onBackPressed)

    val insets = LocalWindowInsets.current
    val bottomInset = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() }
    val rightInset = with(LocalDensity.current) { insets.navigationBars.right.toDp() }
    val leftInset = with(LocalDensity.current) { insets.navigationBars.left.toDp() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColor())
            .padding(
                bottom = bottomInset,
                start = rightInset,
                end = leftInset
            )
    ) {
        Toolbar3(
            title = stringResource(CoreUiR.string.choose_exercise),
            hasNavIcon = true,
            onBackPressed = onBackPressed
        )

        Divider(color = colorResource(id = CoreUiR.color.Separator))

        LazyColumn {
            items(items.size) { index ->
                when (val item = items[index]) {
                    is TrainingCreatorState.MuscleGroupState -> MuscleGroupItem(
                        onChangeExpandState = onChangeExpandState,
                        state = item
                    )
                    is TrainingCreatorState.ExerciseState -> ExerciseItem(item, onExerciseSelected)
                }
            }
        }
    }
}

@Composable
fun MuscleGroupItem(
    state: TrainingCreatorState.MuscleGroupState,
    onChangeExpandState: (MuscleGroupId) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .clickable { onChangeExpandState(state.muscleGroupId) }
            .fillMaxWidth(),
        backgroundColor = colorResource(id = CoreUiR.color.CardBackground),
        elevation = 0.dp
    ) {
        Text(
            text = state.title,
            fontSize = 15.sp,
            color = colorResource(id = CoreUiR.color.PageTextColor),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 16.dp, end = 48.dp)
        )
        Image(
            painter = painterResource(id = CoreUiR.drawable.ic_arrow_up),
            contentDescription = null,
            alignment = Alignment.CenterEnd,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ExerciseItem(
    item: TrainingCreatorState.ExerciseState,
    onExerciseSelected: (ExerciseId) -> Unit
) {
    if (!item.isInExpandedGroup) {
        return
    }

    Column(Modifier.clickable { onExerciseSelected(item.exercise.id) }) {
        Row(Modifier.padding(16.dp)) {
            Checkbox(
                checked = item.isSelected,
                onCheckedChange = { onExerciseSelected(item.exercise.id) },
                modifier = Modifier.align(CenterVertically),
                colors = CheckboxDefaults.colors(
                    uncheckedColor = colorResource(id = CoreUiR.color.CardContent)
                )
            )

            Card(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(48.dp),
                shape = CircleShape,
                elevation = 2.dp,
                backgroundColor = Purple200
            ) {
                Image(
                    painter = painterResource(id = CoreUiR.drawable.ic_gym_dumbbell),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }

            Text(
                text = item.exercise.title,
                color = colorResource(id = CoreUiR.color.PageTextColor),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .align(CenterVertically)
            )
        }
        if (item.hasDivider) {
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = colorResource(id = CoreUiR.color.Separator)
            )
        }
    }
}

@Preview
@Composable
fun ExerciseSelectorPreview() {
    ExerciseSelector(onBackPressed = {}, emptyList(), {}, {})
}