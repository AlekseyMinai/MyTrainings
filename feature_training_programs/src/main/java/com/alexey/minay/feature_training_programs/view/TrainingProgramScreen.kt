package com.alexey.minay.feature_training_programs.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexey.minay.core_training.TrainingProgramId
import com.alexey.minay.core_ui.Toolbar2
import com.alexey.minay.core_ui.gradientColor
import com.alexey.minay.feature_training_programs.domain.TrainingProgram
import com.alexey.minay.feature_training_programs.presentation.TrainingProgramsStore
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingProgramScreen(
    store: TrainingProgramsStore,
    openProgram: (TrainingProgramId) -> Unit,
    createTraining: () -> Unit
) {
    val state by store.state.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .background(brush = gradientColor())
            .padding(bottom = 80.dp)
            .verticalScroll(scrollState)
    ) {
        Toolbar2(title = stringResource(RCoreUi.string.training_program))
        Calendar()
        Programs(state.programs, openProgram, createTraining)
    }
}

@Composable
fun Programs(
    programs: List<TrainingProgram>,
    openProgram: (TrainingProgramId) -> Unit,
    createTraining: () -> Unit
) {
    Box(modifier = Modifier.padding(top = 36.dp)) {
        LazyRow {
            items(programs.size) { index ->
                ProgramItem(programs[index], openProgram)
            }
            item {
                AddGroupItem(createTraining)
            }
        }
    }
}

@Composable
fun ProgramItem(
    program: TrainingProgram,
    openProgram: (TrainingProgramId) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = 0.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .clickable { openProgram(program.programId) }
                .background(colorResource(id = RCoreUi.color.CardBackground))
                .height(212.dp)
                .width(132.dp)
        ) {
            Image(
                painter = painterResource(id = RCoreUi.drawable.ic_thumbnail),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = program.title,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
                    .align(Alignment.BottomStart),
                color = colorResource(id = RCoreUi.color.CardContent),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun AddGroupItem(createTraining: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .clickable { createTraining() }
                .background(colorResource(id = RCoreUi.color.CardBackground))
                .height(212.dp)
                .width(132.dp)
        ) {

        }
    }
}