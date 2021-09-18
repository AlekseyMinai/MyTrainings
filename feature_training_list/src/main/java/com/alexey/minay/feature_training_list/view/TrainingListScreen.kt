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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alesno.mytrainings.ui.common.Toolbar
import com.alexey.minay.feature_training_list.R
import com.alexey.minay.feature_training_list.domain.TrainingInfo
import com.alexey.minay.feature_training_list.domain.TrainingInfoId
import com.alexey.minay.feature_training_list.presentation.TrainingListIntent
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import kotlinx.coroutines.launch

@Composable
fun TrainingListScreen(store: TrainingListStore) {
    val state by store.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Toolbar(title = stringResource(com.alexey.minay.core_ui.R.string.training_list))
        TrainingList(state.trainings) {
            coroutineScope.launch {
                store.accept(TrainingListIntent.StartNewTraining(it))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrainingList(trainings: List<TrainingInfo>, onClick: (id: TrainingInfoId) -> Unit) {
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
private fun TrainingListItem(trainingInfo: TrainingInfo, onClick: () -> Unit) {
    Box(Modifier.padding(4.dp)) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .border(
                    width = 1.dp,
                    color = colorResource(id = com.alexey.minay.core_ui.R.color.purple_500),
                    shape = RoundedCornerShape(4.dp),
                ).fillMaxSize()
        ) {
            Text(
                text = trainingInfo.name,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 36.dp, bottom = 36.dp)
                    .align(Alignment.Center)
            )
        }
    }
}