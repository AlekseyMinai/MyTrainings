package com.alexey.minay.feature_training_list.view

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_ui.Toolbar2
import com.alexey.minay.feature_training_list.domain.TrainingType
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingListScreen(
    store: TrainingListStore,
    startTraining: (trainingId: com.alexey.minay.core_training.TrainingTypeId) -> Unit
) {
    val state by store.state.collectAsState()

    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = RCoreUi.color.PageBackground1),
                        colorResource(id = RCoreUi.color.PageBackground2),
                    )
                )
            )
            .fillMaxSize()
    ) {
        Toolbar2(title = stringResource(RCoreUi.string.training_list))
        TrainingList(state.trainings) {
            startTraining(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrainingList(
    trainings: List<TrainingType>,
    onClick: (id: com.alexey.minay.core_training.TrainingTypeId) -> Unit
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
private fun TrainingListItem(trainingType: TrainingType, onClick: () -> Unit) {
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
                painter = painterResource(id = RCoreUi.drawable.ic_thumbnail),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
            )
            Box {
                Text(
                    text = trainingType.title,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    color = colorResource(id = RCoreUi.color.CardContent)
                )
            }
        }
    }
}