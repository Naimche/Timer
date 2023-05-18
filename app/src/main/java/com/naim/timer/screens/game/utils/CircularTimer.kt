package com.naim.timer.screens.game.utils

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naim.timer.ui.theme.TimerTheme
import kotlinx.coroutines.delay

@Composable
fun CircularTimer(
    modifier: Modifier = Modifier
) {
    Box(modifier.size(250.dp), contentAlignment = Alignment.Center) {
        RoundedCircularProgressIndicator(
            progress = 0.5f,
            modifier = Modifier.fillMaxSize(),
            strokeWidth = 15.dp
        )
        Text(text = "00:00:00")
    }
}

@Composable
fun TimerStartButton() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Start Timer"
        )
    }
}

@Composable
fun RoundedCircularProgressIndicator(
    /*@FloatRange(from = 0.0, to = 1.0)*/
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    backgroundColor: Color = Color.Transparent,
    strokeCap: StrokeCap = StrokeCap.Butt,
) {
    val coercedProgress = progress.coerceIn(0f, 1f)
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }
    androidx.compose.foundation.Canvas(
        modifier
            .progressSemantics(coercedProgress)
            .size(CircularIndicatorDiameter)
    ) {
        // Start at 12 O'clock
        val startAngle = 270f
        val sweep = coercedProgress * 360f
        drawCircularIndicatorBackground(backgroundColor, stroke)
        drawDeterminateCircularIndicator(startAngle, sweep, color, stroke)
    }
}

private val CircularIndicatorDiameter = 40.dp


private fun DrawScope.drawCircularIndicatorBackground(
    color: Color,
    stroke: Stroke
) = drawCircularIndicator(0f, 360f, color, stroke)

private fun DrawScope.drawDeterminateCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) = drawCircularIndicator(startAngle, sweep, color, stroke)

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}

@Composable
fun CircularTimerWithBackground(
    progress: Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    foreGroundColor: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.primary.copy(alpha = 0.3f),
) {
    val animatedProgress by animateFloatAsState(targetValue = progress)
    val animatedProgressRef = remember { mutableStateOf(0f) }

    LaunchedEffect(animatedProgress) {
        animateValue(animatedProgress, animatedProgressRef)
    }

    Box(modifier = modifier.size(200.dp)) {
        RoundedCircularProgressIndicator(
            progress = animatedProgressRef.value,
            strokeWidth = strokeWidth,
            color = foreGroundColor,
            modifier = Modifier.fillMaxSize()
        )
        RoundedCircularProgressIndicator(
            progress = 1f,
            strokeWidth = strokeWidth,
            color = backgroundColor,
            modifier = Modifier.fillMaxSize()
        )
    }
}


private suspend fun animateValue(targetValue: Float, animatedValue: MutableState<Float>) {
    val animationSteps = 60 // Number of animation steps
    val stepDuration = 16L // Duration of each step in milliseconds

    val stepSize = (targetValue - animatedValue.value) / animationSteps

    repeat(animationSteps) {
        animatedValue.value += stepSize
        delay(stepDuration)
    }
}

@Preview
@Composable
private fun ScreenContentPreview() {
    TimerTheme() {
        Surface {
            CircularTimerWithBackground(progress = 0.5f,)
        }

    }
}