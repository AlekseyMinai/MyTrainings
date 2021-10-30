package com.alesno.mytrainings

import android.content.res.Configuration
import android.graphics.Paint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_ui.Toolbar2
import com.alexey.minay.core_ui.gradientColor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import com.alexey.minay.core_ui.R as RCoreUi

@Composable
fun TrainingGroupScreen() {
    val date = Date()
    val calendar = Calendar.getInstance()
    calendar.time = date
    val day = SimpleDateFormat("dd", Locale.getDefault()).format(date)
    val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(date)

    val colorPositive = colorResource(id = RCoreUi.color.CalendarPositive)
    val colorNeutral = colorResource(id = RCoreUi.color.CalendarNeutral)
    val colorDate = colorResource(id = RCoreUi.color.CalendarDate)
    val colorDatePointer = colorResource(id = RCoreUi.color.CalendarDatePointer)
    val configuration = LocalConfiguration.current

    val screenWidth = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> configuration.screenHeightDp.dp
        else -> configuration.screenWidthDp.dp
    }
    val screenWidthPx = with(LocalDensity.current) { screenWidth.roundToPx().toFloat() }
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .background(brush = gradientColor())
            .padding(bottom = 80.dp)
            .verticalScroll(scrollState)
    ) {
        Toolbar2(title = stringResource(RCoreUi.string.training_program))

        Box(modifier = Modifier.height(screenWidth - 100.dp)) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val topOffset = 160f
                val offsetY = screenWidthPx / 2 - topOffset
                val horizontalOffset = 120f
                val offsetX = screenWidthPx / 2

                val radiusOut = screenWidthPx / 2 - horizontalOffset * 2
                val strokeWidthOut = 50f

                val strokeWidthIn = 40f
                val radiusIn = radiusOut - strokeWidthOut

                val pointerRadius = 30f

                val dateAngle = calendar.get(Calendar.DAY_OF_MONTH).toFloat() /
                        calendar.getActualMaximum(Calendar.DAY_OF_MONTH) * 360
                val startAngle = 270f

                drawCircle(
                    color = colorPositive,
                    center = Offset(offsetX, offsetY),
                    radius = radiusOut,
                    style = Stroke(
                        width = strokeWidthOut
                    )
                )

                drawCircle(
                    color = colorNeutral,
                    center = Offset(offsetX, offsetY),
                    radius = radiusIn,
                    style = Stroke(
                        width = strokeWidthIn
                    )
                )

                drawArc(
                    color = colorDate,
                    topLeft = Offset(offsetX - radiusIn, offsetY - radiusIn),
                    size = Size(radiusIn * 2, radiusIn * 2),
                    style = Stroke(
                        width = strokeWidthIn
                    ),
                    startAngle = startAngle,
                    sweepAngle = dateAngle,
                    useCenter = false
                )

                val offsetPointerX = radiusIn * sin(dateAngle * PI / 180).toFloat()
                val offsetPointerY = radiusIn * cos(dateAngle * PI / 180).toFloat()

                drawCircle(
                    color = colorDatePointer,
                    center = Offset(offsetX + offsetPointerX, offsetY - offsetPointerY),
                    radius = pointerRadius
                )

                val paint = Paint()
                paint.textAlign = Paint.Align.CENTER
                paint.color = 0x77ffffff
                drawIntoCanvas {
                    paint.textSize = 220f
                    it.nativeCanvas.drawText(day, offsetX, offsetY + 16f, paint)

                    paint.textSize = 68f
                    it.nativeCanvas.drawText(month, offsetX, offsetY + 120f, paint)
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            val list = listOf(1, 2, 3)
            LazyRow {
                items(list.size) { index ->
                    GroupItem(index == list.lastIndex)
                }
            }
        }
    }
}

@Composable
fun GroupItem(isLast: Boolean) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val paddingEnd = when {
        isLast -> 16.dp
        else -> 0.dp
    }
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = paddingEnd)
            .width(screenWidth - 48.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Column(
            modifier = Modifier
                .clickable { }
                .background(colorResource(id = RCoreUi.color.CardBackground))
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = RCoreUi.drawable.ic_thumbnail),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Text(
                text = "Трехдневный сплит на силу",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp, bottom = 40.dp),
                color = colorResource(id = RCoreUi.color.CardContent)
            )
        }
    }
}