package com.alesno.mytrainings.training

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alesno.mytrainings.R
import com.alesno.mytrainings.domain.TrainingExercise
import com.alesno.mytrainings.domain.TrainingExerciseId
import com.alesno.mytrainings.domain.TrainingSet
import com.alesno.mytrainings.fake.FakeData
import com.alesno.mytrainings.training.presentation.TrainingIntent
import com.alesno.mytrainings.training.presentation.TrainingStore
import kotlinx.coroutines.launch

@Composable
fun TrainingScreen(store: TrainingStore) {
    val state by store.trainingState.collectAsState(FakeData.createTraining())
    Column(modifier = Modifier.fillMaxWidth()) {
        val coroutineStore = rememberCoroutineScope()
        TrainingExercises(exercises = state.exercises) {
            coroutineStore.launch {
                store.proceed(TrainingIntent.AddSet(it, 50, 12))
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
        item {
            Box(Modifier.padding(vertical = 16.dp))
        }
        items(exercises.size) { index ->
            Text(exercises[index].info.name, modifier = Modifier.padding(start = 8.dp, bottom = 16.dp))
            TrainingExercise(exercise = exercises[index], onNewSetClicked)
        }
    }
}

@Composable
fun TrainingExercise(exercise: TrainingExercise, onNewSetClicked: (TrainingExerciseId) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(Modifier.padding(top = 12.dp, bottom = 12.dp, start = 12.dp)) {
            Card(
                modifier = Modifier.size(52.dp),
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
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                TrainingSets(trainingSets = exercise.sets) { onNewSetClicked(exercise.id) }
            }
        }
    }
}

@Composable
fun TrainingSets(trainingSets: List<TrainingSet>, onNewSetClicked: () -> Unit) {
    LazyRow {
        item {
            Column {
                Column(Modifier.padding(start = 8.dp, end = 16.dp)) {
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
        item {
            Box(modifier = Modifier.padding(start = 8.dp, end = 16.dp)) {
                Button(
                    onClick = { onNewSetClicked() },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(42.dp)
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
fun TrainingSet(trainingSet: TrainingSet) {
    Column(Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp)) {
        Text(
            text = trainingSet.weight.toString(),
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(text = trainingSet.count.toString())
    }
}