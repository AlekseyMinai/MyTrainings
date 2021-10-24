package com.alexey.minay.feature_training_history.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexey.minay.core_training.TrainingId
import com.alexey.minay.core_ui.Toolbar2
import com.alexey.minay.core_ui.gradientColor
import com.alexey.minay.core_utils.toStringFormat
import com.alexey.minay.feature_training_history.R
import com.alexey.minay.feature_training_history.domain.Training
import com.alexey.minay.feature_training_history.presentation.TrainingHistoryStore
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingHistory(
    store: TrainingHistoryStore,
    startTraining: (trainingId: TrainingId) -> Unit
) {
    val state by store.state.collectAsState()

    Column(
        modifier = Modifier
            .background(
                brush = gradientColor()
            )
            .fillMaxSize()
    ) {
        Toolbar2(title = stringResource(RCoreUi.string.training_history))
        TrainingList(state.trainings) {
            startTraining(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrainingList(
    trainings: List<Training>,
    onClick: (id: TrainingId) -> Unit
) {
    LazyColumn {
        items(trainings.size) {
            TrainingListItem(trainings[it]) {
                onClick(trainings[it].id)
            }
        }
    }
}

@Composable
private fun TrainingListItem(training: Training, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .background(colorResource(id = RCoreUi.color.CardBackground))
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_thumbnail_history),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
            )
            Column {
                Text(
                    text = training.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp),
                    color = colorResource(id = RCoreUi.color.CardContent)
                )
                Text(
                    text = training.date.toStringFormat(),
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 12.dp),
                    color = colorResource(id = RCoreUi.color.CardContent)
                )
            }
        }
    }
}