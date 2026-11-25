package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * Simplified chromosome-position schematic for the staged cell-division lesson.
 * It deliberately illustrates chromosome relationships, not microscopic detail.
 */
@Composable
fun CellDivisionDiagram(
    type: CellDivisionType,
    stageIndex: Int,
    modifier: Modifier = Modifier
) {
    Canvas(modifier.fillMaxWidth().height(220.dp)) {
        val mitosis = type == CellDivisionType.MITOSIS
        val finalStage = if (mitosis) 5 else 8
        val cellCount = when {
            stageIndex >= finalStage -> if (mitosis) 2 else 4
            !mitosis && stageIndex >= 5 -> 2
            else -> 1
        }
        val width = size.width
        val height = size.height
        val gap = width * 0.035f
        val cellWidth = (width - gap * (cellCount + 1)) / cellCount
        val cellHeight = height * if (cellCount >= 4) 0.67f else 0.78f
        val top = (height - cellHeight) / 2f
        val replicated = when {
            mitosis -> stageIndex in 1..3
            else -> stageIndex in 1..7
        }
        val separatingSisters = (mitosis && stageIndex == 4) || (!mitosis && stageIndex == 7)
        val separatingHomologues = !mitosis && stageIndex == 4
        val chromosomeNumber = if (mitosis) 4 else 2
        val daughterCells = cellCount

        repeat(daughterCells) { cellIndex ->
            val left = gap + cellIndex * (cellWidth + gap)
            val right = left + cellWidth
            val centerX = (left + right) / 2f
            val centerY = top + cellHeight / 2f
            drawOval(
                color = Color(0xFF7A8795),
                topLeft = Offset(left, top),
                size = androidx.compose.ui.geometry.Size(cellWidth, cellHeight),
                style = Stroke(width = 2.5f)
            )
            val compact = cellCount >= 4
            val chromosomeScale = if (compact) 0.45f else 0.72f
            val chromHeight = cellHeight * 0.22f * chromosomeScale
            val chromWidth = cellWidth * 0.11f * chromosomeScale
            val perPole = if (separatingSisters) chromosomeNumber else chromosomeNumber / 2

            if (separatingSisters || separatingHomologues) {
                repeat(perPole) { i ->
                    val offsetY = (i - (perPole - 1) / 2f) * cellHeight * 0.19f
                    val color = if (i % 2 == 0) Color(0xFF2878B5) else Color(0xFFE18A35)
                    val x1 = centerX - cellWidth * 0.27f
                    val x2 = centerX + cellWidth * 0.27f
                    if (separatingHomologues) {
                        drawReplicatedChromosome(x1, centerY + offsetY, chromWidth, chromHeight, color)
                        drawReplicatedChromosome(x2, centerY + offsetY, chromWidth, chromHeight, color)
                    } else {
                        drawChromatid(x1, centerY + offsetY, chromHeight, color)
                        drawChromatid(x2, centerY + offsetY, chromHeight, color)
                    }
                }
            } else {
                val count = if (replicated) chromosomeNumber else chromosomeNumber
                repeat(count) { i ->
                    val cols = if (count <= 2) count else 2
                    val rows = (count + cols - 1) / cols
                    val col = i % cols
                    val row = i / cols
                    val x = centerX + (col - (cols - 1) / 2f) * cellWidth * 0.25f
                    val y = centerY + (row - (rows - 1) / 2f) * cellHeight * 0.25f
                    val color = if (i % 2 == 0) Color(0xFF2878B5) else Color(0xFFE18A35)
                    if (replicated) drawReplicatedChromosome(x, y, chromWidth, chromHeight, color)
                    else drawChromatid(x, y, chromHeight, color)
                }
            }
            if (cellCount == 1 && (stageIndex in 2..4 || (!mitosis && stageIndex in 2..4))) {
                val poleY = top + cellHeight * 0.12f
                drawCircle(Color(0xFF687786), radius = cellWidth * 0.025f, center = Offset(centerX, poleY))
                drawCircle(Color(0xFF687786), radius = cellWidth * 0.025f, center = Offset(centerX, top + cellHeight * 0.88f))
            }
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawReplicatedChromosome(
    x: Float, y: Float, halfWidth: Float, halfHeight: Float, color: Color
) {
    drawLine(color, Offset(x - halfWidth, y - halfHeight), Offset(x + halfWidth, y + halfHeight), strokeWidth = 5f)
    drawLine(color, Offset(x - halfWidth, y + halfHeight), Offset(x + halfWidth, y - halfHeight), strokeWidth = 5f)
    drawCircle(Color(0xFF263746), radius = 2.5f, center = Offset(x, y))
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawChromatid(
    x: Float, y: Float, halfHeight: Float, color: Color
) {
    drawLine(color, Offset(x, y - halfHeight), Offset(x, y + halfHeight), strokeWidth = 5f)
    drawCircle(Color(0xFF263746), radius = 2.5f, center = Offset(x, y))
}
