package com.alexey.minay.feature_training_list.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_ui.Toolbar
import com.alexey.minay.feature_training_list.domain.TrainingType
import com.alexey.minay.feature_training_list.presentation.TrainingListStore

@Composable
fun TrainingListScreen(
    store: TrainingListStore,
    startTraining: (trainingId: com.alexey.minay.core_training.TrainingTypeId) -> Unit
) {
    val state by store.state.collectAsState()

    Column {
        Toolbar(title = stringResource(com.alexey.minay.core_ui.R.string.training_list))
        TrainingList(state.trainings) {
            startTraining(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrainingList(trainings: List<TrainingType>, onClick: (id: com.alexey.minay.core_training.TrainingTypeId) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(trainings.size) {
            TrainingListItem(trainings[it]) {
                onClick(trainings[it].id)
            }
        }
    }
}

@Composable
private fun TrainingListItem(trainingType: TrainingType, onClick: () -> Unit) {
    Box(Modifier.padding(4.dp)) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .border(
                    width = 1.dp,
                    color = colorResource(id = com.alexey.minay.core_ui.R.color.purple_500),
                    shape = RoundedCornerShape(4.dp),
                )
                .fillMaxSize()
        ) {
            Text(
                text = trainingType.title,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 36.dp, bottom = 36.dp)
                    .align(Alignment.Center)
            )
        }
    }
}