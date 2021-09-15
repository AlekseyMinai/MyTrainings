package com.alesno.mytrainings.ui.trainingList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alesno.mytrainings.R
import com.alesno.mytrainings.domain.trainingList.TrainingInfo
import com.alesno.mytrainings.domain.trainingList.TrainingInfoId
import com.alesno.mytrainings.presentation.trainingList.TrainingListStore
import com.alesno.mytrainings.ui.common.Toolbar

@Composable
fun TrainingListScreen(store: TrainingListStore) {
    val state by store.state.collectAsState()
    Column {
        Toolbar(title = stringResource(R.string.training_list))
        TrainingList(state.trainings) {

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
    Box(modifier = Modifier.clickable { onClick() }) {
        Text(
            text = trainingInfo.name,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 36.dp, bottom = 36.dp)
        )
    }
}