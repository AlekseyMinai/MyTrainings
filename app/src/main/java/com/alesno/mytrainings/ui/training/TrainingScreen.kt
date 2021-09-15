package com.alesno.mytrainings.ui.training

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.*
import com.alesno.mytrainings.R
import com.alesno.mytrainings.domain.training.TrainingExercise
import com.alesno.mytrainings.domain.training.TrainingExerciseId
import com.alesno.mytrainings.domain.training.TrainingSet
import com.alesno.mytrainings.ui.common.Toolbar
import com.alesno.mytrainings.presentation.training.TrainingIntent
import com.alesno.mytrainings.presentation.training.TrainingStore
import kotlinx.coroutines.launch

@Composable
fun TrainingScreen(store: TrainingStore) {
    val state by store.state.collectAsState()
    val coroutineStore = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        Toolbar(title = state.training.name)
        TrainingExercises(exercises = state.training.exercises) {
            coroutineStore.launch {
                store.accept(TrainingIntent.AddSet(it, 50, 12))
            }
        }
    }
}

@Composable
fun TrainingExercises(
    exercises: List<TrainingExercise>,
    onNewSetClicked: (TrainingExerciseId) -> Unit
) {
    LazyColumn {
        items(exercises.size) { index ->
            TrainingExercise(exercise = exercises[index], onNewSetClicked)
        }
    }
}

@Composable
fun TrainingExercise(exercise: TrainingExercise, onNewSetClicked: (TrainingExerciseId) -> Unit) {
    Text(
        text = exercise.info.name,
        color = Color.White,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
    )
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        ConstraintLayout(Modifier.padding(top = 16.dp, bottom = 12.dp)) {
            val (list, button) = createRefs()
            TrainingSets(
                trainingSets = exercise.sets,
                modifier = Modifier.constrainAs(list) {
                    start.linkTo(parent.start)
                    end.linkTo(button.start, margin = 8.dp)
                    top.linkTo(parent.top)
                    width = Companion.fillToConstraints
                }
            )
            Box(
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end, margin = 8.dp)
                        width = Companion.preferredWrapContent
                    }
            ) {
                Button(
                    onClick = { onNewSetClicked(exercise.id) },
                    shape = CircleShape,
                    modifier = Modifier.size(42.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Localized description",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun TrainingSets(trainingSets: List<TrainingSet>, modifier: Modifier) {
    LazyRow(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.width(8.dp))
        }
        item {
            Card(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                elevation = 2.dp,
                backgroundColor = MaterialTheme.colors.onSurface
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gym_dumbbell),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }
        item {
            Column {
                Column(Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp)) {
                    Text(
                        text = "Вес",
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(text = "Подходы")
                }
            }
        }
        items(trainingSets.size) { index ->
            TrainingSet(trainingSets[index])
        }
    }
}

@Composable
fun TrainingSet(trainingSet: TrainingSet) {
    Column(Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp)) {
        Text(
            text = trainingSet.weight.toString(),
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(text = trainingSet.count.toString())
    }
}