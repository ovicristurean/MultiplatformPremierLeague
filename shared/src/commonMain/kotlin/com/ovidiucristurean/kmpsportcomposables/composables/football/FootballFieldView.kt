package com.ovidiucristurean.kmpsportcomposables.composables.football

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun FootballFieldView(
    modifier: Modifier,
    fieldColor: Color = Color.LightGray,
    edgesColor: Color = Color.White
) {
    Box(modifier = modifier.fillMaxSize()
        .aspectRatio(1 / 2f)
        .clip(RoundedCornerShape(8.dp))
        .background(fieldColor)
        .drawBehind {
            drawRoundRect(
                color = edgesColor,
                cornerRadius = CornerRadius(8.dp.toPx()),
                style = Stroke(2.dp.toPx())
            )
            drawHomePenaltyBox(
                edgesColor = edgesColor
            )
            drawCenterArea(
                edgesColor = edgesColor
            )
            drawAwayPenaltyBox(
                edgesColor = edgesColor
            )
            drawCorners(
                edgesColor = edgesColor
            )
        })
}

private fun DrawScope.drawHomePenaltyBox(edgesColor: Color) {
    val bigBoxArea = Path()
    bigBoxArea.moveTo(x = size.width * PENALTY_BOX_MARGIN, y = 0f)
    bigBoxArea.lineTo(size.width * PENALTY_BOX_MARGIN, size.width * PENALTY_BOX_HEIGHT)
    bigBoxArea.lineTo(
        size.width * (PENALTY_BOX_MARGIN + PENALTY_BOX_WIDTH),
        size.width * PENALTY_BOX_HEIGHT
    )
    bigBoxArea.lineTo(size.width * (PENALTY_BOX_MARGIN + PENALTY_BOX_WIDTH), 0f)

    val centerX = size.width * 0.5f
    val centerY = size.width * PENALTY_POINT_MARGIN
    val radius = size.width * PENALTY_BOX_ARC_RADIUS

    val startAngle = 35f
    val sweepAngle = 110f

    bigBoxArea.arcTo(
        rect = Rect(center = Offset(centerX, centerY), radius = radius),
        startAngleDegrees = startAngle,
        sweepAngleDegrees = sweepAngle,
        forceMoveTo = true
    )

    drawPath(bigBoxArea, color = edgesColor, style = Stroke(2.dp.toPx()))

    val smallBoxArea = Path()
    smallBoxArea.moveTo(x = size.width * (1f - GOAL_AREA_WIDTH) / 2, y = 0f)
    smallBoxArea.lineTo(size.width * (1f - GOAL_AREA_WIDTH) / 2, size.width * GOAL_AREA_HEIGHT)
    smallBoxArea.lineTo(
        size.width * ((1f - GOAL_AREA_WIDTH) / 2 + GOAL_AREA_WIDTH),
        size.width * GOAL_AREA_HEIGHT
    )
    smallBoxArea.lineTo(size.width * ((1f - GOAL_AREA_WIDTH) / 2 + GOAL_AREA_WIDTH), 0f)

    drawPath(smallBoxArea, color = edgesColor, style = Stroke(2.dp.toPx()))

    drawCircle(
        color = edgesColor,
        radius = 6.dp.toPx(),
        center = Offset(x = size.width * 0.5f, y = size.width * PENALTY_POINT_MARGIN)
    )
}

private fun DrawScope.drawCenterArea(edgesColor: Color) {
    drawLine(
        color = edgesColor,
        start = Offset(0f, size.height / 2f),
        end = Offset(size.width, size.height / 2f),
        strokeWidth = 2.dp.toPx()
    )
    drawCircle(
        color = edgesColor,
        radius = 6.dp.toPx(),
    )
    drawCircle(
        color = edgesColor,
        radius = size.width * CENTER_CIRCLE_RADIUS,
        style = Stroke(2.dp.toPx())
    )
}

private fun DrawScope.drawAwayPenaltyBox(edgesColor: Color) {
    val bigBoxArea = Path()
    bigBoxArea.moveTo(size.width * PENALTY_BOX_MARGIN, size.height)
    bigBoxArea.lineTo(
        size.width * PENALTY_BOX_MARGIN,
        size.height - size.width * PENALTY_BOX_HEIGHT
    )
    bigBoxArea.lineTo(
        size.width * (PENALTY_BOX_MARGIN + PENALTY_BOX_WIDTH),
        size.height - size.width * PENALTY_BOX_HEIGHT
    )
    bigBoxArea.lineTo(size.width * (PENALTY_BOX_MARGIN + PENALTY_BOX_WIDTH), size.height)

    val centerX = size.width * 0.5f
    val centerY = size.height - size.width * PENALTY_POINT_MARGIN
    val radius = size.width * PENALTY_BOX_ARC_RADIUS

    val startAngle = 215f
    val sweepAngle = 110f

    bigBoxArea.arcTo(
        rect = Rect(center = Offset(centerX, centerY), radius = radius),
        startAngleDegrees = startAngle,
        sweepAngleDegrees = sweepAngle,
        forceMoveTo = true
    )

    drawPath(bigBoxArea, color = edgesColor, style = Stroke(2.dp.toPx()))

    val smallBoxArea = Path()
    smallBoxArea.moveTo(x = size.width * (1f - GOAL_AREA_WIDTH) / 2, y = size.height)
    smallBoxArea.lineTo(
        size.width * (1f - GOAL_AREA_WIDTH) / 2,
        size.height - size.width * GOAL_AREA_HEIGHT
    )
    smallBoxArea.lineTo(
        size.width * (1f - GOAL_AREA_WIDTH) / 2 + size.width * GOAL_AREA_WIDTH,
        size.height - size.width * GOAL_AREA_HEIGHT
    )
    smallBoxArea.lineTo(
        size.width * (1f - GOAL_AREA_WIDTH) / 2 + size.width * GOAL_AREA_WIDTH,
        size.height
    )
    drawPath(smallBoxArea, color = edgesColor, style = Stroke(2.dp.toPx()))

    drawCircle(
        color = edgesColor,
        radius = 6.dp.toPx(),
        center = Offset(
            x = size.width * 0.5f,
            y = size.height - size.width * PENALTY_POINT_MARGIN
        )
    )
}

private fun DrawScope.drawCorners(edgesColor: Color) {
    drawArc(
        color = edgesColor,
        startAngle = 0f,
        sweepAngle = 90f,
        useCenter = false,
        style = Stroke(2.dp.toPx()),
        size = Size(size.width * CORNER_CIRCLE_SIZE, size.width * CORNER_CIRCLE_SIZE),
        topLeft = Offset(
            -(size.width * CORNER_CIRCLE_SIZE) / 2,
            -(size.width * CORNER_CIRCLE_SIZE) / 2
        )
    )

    drawArc(
        color = edgesColor,
        startAngle = 90f,
        sweepAngle = 90f,
        useCenter = false,
        style = Stroke(2.dp.toPx()),
        size = Size(size.width * CORNER_CIRCLE_SIZE, size.width * CORNER_CIRCLE_SIZE),
        topLeft = Offset(
            size.width - (size.width * CORNER_CIRCLE_SIZE) / 2,
            -(size.width * CORNER_CIRCLE_SIZE) / 2
        )
    )

    drawArc(
        color = edgesColor,
        startAngle = 270f,
        sweepAngle = 90f,
        useCenter = false,
        style = Stroke(2.dp.toPx()),
        size = Size(size.width * CORNER_CIRCLE_SIZE, size.width * CORNER_CIRCLE_SIZE),
        topLeft = Offset(
            -(size.width * CORNER_CIRCLE_SIZE) / 2,
            size.height - (size.width * CORNER_CIRCLE_SIZE) / 2
        )
    )

    drawArc(
        color = edgesColor,
        startAngle = 180f,
        sweepAngle = 90f,
        useCenter = false,
        style = Stroke(2.dp.toPx()),
        size = Size(size.width * CORNER_CIRCLE_SIZE, size.width * CORNER_CIRCLE_SIZE),
        topLeft = Offset(
            size.width - (size.width * CORNER_CIRCLE_SIZE) / 2,
            size.height - (size.width * CORNER_CIRCLE_SIZE) / 2
        )
    )
}

const val PENALTY_BOX_WIDTH = 0.8f
const val PENALTY_BOX_HEIGHT = 0.33f
const val PENALTY_BOX_MARGIN = 0.1f
const val GOAL_AREA_WIDTH = 0.36f
const val GOAL_AREA_HEIGHT = 0.11f
const val PENALTY_BOX_ARC_RADIUS = 0.19f
const val PENALTY_POINT_MARGIN = 0.22f
const val CENTER_CIRCLE_RADIUS = 0.2f
const val CORNER_CIRCLE_SIZE = 0.08f
