package com.alexey.minay.feature_training_list.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.core_training.TrainingTypeId
import com.alexey.minay.core_ui.BackHandler
import com.alexey.minay.core_ui.Toolbar2
import com.alexey.minay.core_ui.gradientColor
import com.alexey.minay.feature_training_list.domain.TrainingType
import com.alexey.minay.feature_training_list.presentation.TrainingListStore
import com.google.accompanist.insets.LocalWindowInsets
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingListScreen(
    store: TrainingListStore,
    startTraining: (trainingId: TrainingTypeId) -> Unit,
    onBackPressed: () -> Unit,
    createTraining: (TrainingProgramId) -> Unit
) {
    val state by store.state.collectAsState()

    BackHandler(onBack = onBackPressed)

    val insets = LocalWindowInsets.current
    val bottomInset = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() }

    Box {
        Column(
            modifier = Modifier
                .background(
                    brush = gradientColor()
                )
                .fillMaxSize()
        ) {
            Toolbar2(title = stringResource(RCoreUi.string.training_list))
            TrainingList(
                trainings = state.trainings,
                startTraining = { startTraining(it) }
            )
        }

        FloatingActionButton(
            onClick = { createTraining(state.programId) },
            backgroundColor = colorResource(id = RCoreUi.color.Button),
            contentColor = colorResource(RCoreUi.color.CardContent),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = dimensionResource(RCoreUi.dimen.page_horizontal_padding),
                    bottom = 16.dp + bottomInset
                )
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrainingList(
    trainings: List<TrainingType>,
    startTraining: (id: TrainingTypeId) -> Unit
) {
    LazyColumn {
        items(trainings.size) {
            TrainingListItem(
                trainingType = trainings[it],
                onClick = { startTraining(trainings[it].id) }
            )
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
            Text(
                text = trainingType.title,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                color = colorResource(id = RCoreUi.color.CardContent)
            )
        }
    }
}