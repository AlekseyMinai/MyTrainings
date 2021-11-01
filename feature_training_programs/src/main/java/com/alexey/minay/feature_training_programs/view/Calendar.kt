package com.alexey.minay.feature_training_programs.view

import android.content.res.Configuration
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.alexey.minay.core_ui.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Calendar() {
    val date = Date()
    val calendar = Calendar.getInstance()
    calendar.time = date
    val day = SimpleDateFormat("dd", Locale.getDefault()).format(date)
    val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(date)

    val colorPositive = colorResource(id = R.color.CalendarPositive)
    val colorNeutral = colorResource(id = R.color.CalendarNeutral)
    val colorDate = colorResource(id = R.color.CalendarDate)
    val colorDatePointer = colorResource(id = R.color.CalendarDatePointer)
    val configuration = LocalConfiguration.current

    val screenWidth = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> configuration.screenHeightDp.dp
        else -> configuration.screenWidthDp.dp
    }
    val screenWidthPx = with(LocalDensity.current) { screenWidth.roundToPx().toFloat() }

    Box(modifier = Modifier.height(screenWidth - 100.dp)) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val topOffset = 120f
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
}