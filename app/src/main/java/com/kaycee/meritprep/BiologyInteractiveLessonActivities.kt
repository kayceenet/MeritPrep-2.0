package com.kaycee.meritprep

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

/** Guided, offline-capable interactive activities embedded in relevant lessons. */
@Composable
fun BiologyInteractiveLessonActivity(topicId: String) {
    when (topicId) {
        "bio-transport" -> DiffusionAnimatedLessonActivity()
        "bio-nutrition" -> PhotosynthesisInquiryActivity()
    }
}

@Composable
private fun DiffusionAnimatedLessonActivity() {
    var left by remember { mutableFloatStateOf(1.0f) }
    var right by remember { mutableFloatStateOf(0.2f) }
    var permeability by remember { mutableFloatStateOf(80f) }
    val result = remember(left, right, permeability) {
        DiffusionSimulation.evaluate(DiffusionConditions(left.toDouble(), right.toDouble(), permeability.toDouble()))
    }
    val transition = rememberInfiniteTransition(label = "diffusion-particles")
    val phase by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Restart), label = "particle-motion")
    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Interactive concept animation: diffusion", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change the concentration on each side and the membrane permeability. Watch the particle model and compare random movement with net diffusion.")
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                val boundary = size.width / 2f
                val top = size.height * 0.12f
                val bottom = size.height * 0.88f
                drawLine(Color(0xFF607D8B), Offset(boundary, top), Offset(boundary, bottom), strokeWidth = 7f)
                val leftCount = (left * 12).toInt().coerceIn(1, 12)
                val rightCount = (right * 12).toInt().coerceIn(1, 12)
                repeat(leftCount) { i ->
                    val y = top + (bottom - top) * ((i + 0.5f) / leftCount)
                    val crosses = left > right && permeability > 0f
                    val p = (phase + i * 0.13f) % 1f
                    val x = if (crosses) size.width * (0.08f + 0.84f * p) else size.width * (0.12f + 0.28f * ((i % 4) / 3f)) + sin((phase + i) * (2f * PI).toFloat()) * 5f
                    drawCircle(Color(0xFF1976D2), 6f, Offset(x.coerceIn(8f, size.width - 8f), y))
                }
                repeat(rightCount) { i ->
                    val y = top + (bottom - top) * ((i + 0.5f) / rightCount)
                    val crosses = right > left && permeability > 0f
                    val p = (phase + i * 0.13f) % 1f
                    val x = if (crosses) size.width * (0.92f - 0.84f * p) else size.width * (0.60f + 0.28f * ((i % 4) / 3f)) + sin((phase + i) * (2f * PI).toFloat()) * 5f
                    drawCircle(Color(0xFFEF8C32), 6f, Offset(x.coerceIn(8f, size.width - 8f), y))
                }
            }
            Text("Left concentration: ${"%.1f".format(left)}")
            Slider(value = left, onValueChange = { left = it }, valueRange = 0f..1.5f)
            Text("Right concentration: ${"%.1f".format(right)}")
            Slider(value = right, onValueChange = { right = it }, valueRange = 0f..1.5f)
            Text("Membrane permeability: ${permeability.toInt()}%")
            Slider(value = permeability, onValueChange = { permeability = it }, valueRange = 0f..100f)
            HorizontalDivider()
            Text(
                when (result.direction) {
                    DiffusionDirection.HIGH_TO_LOW -> if (left > right) "Net movement: left → right" else "Net movement: right → left"
                    DiffusionDirection.NO_NET_MOVEMENT -> "No net movement; particles still move randomly in both directions"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text("Concentration gradient: ${"%.2f".format(result.concentrationGradient)}")
            Text("Relative rate index: ${"%.0f".format(result.relativeRateIndexPercent)}%")
            Text(result.explanation)
            Text("Learning check: If both sides reach the same concentration, do particles stop moving? Explain random movement versus net diffusion.", fontWeight = FontWeight.SemiBold)
            Text("Model limit: particle positions are animated for visual learning. The rate index is simplified and is not a measured diffusion rate.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun PhotosynthesisInquiryActivity() {
    var light by remember { mutableFloatStateOf(35f) }
    var carbonDioxide by remember { mutableFloatStateOf(55f) }
    var temperature by remember { mutableFloatStateOf(25f) }
    val result = remember(light, carbonDioxide, temperature) {
        PhotosynthesisSimulation.evaluate(PhotosynthesisConditions(light.toDouble(), carbonDioxide.toDouble(), temperature.toDouble()))
    }
    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Interactive investigation: factors affecting photosynthesis", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one factor at a time. Observe how the limiting factor changes as another factor increases.")
            Text("Light intensity: ${light.toInt()}%")
            Slider(value = light, onValueChange = { light = it }, valueRange = 0f..100f)
            Text("Carbon dioxide availability: ${carbonDioxide.toInt()}%")
            Slider(value = carbonDioxide, onValueChange = { carbonDioxide = it }, valueRange = 0f..100f)
            Text("Temperature: ${temperature.toInt()} °C")
            Slider(value = temperature, onValueChange = { temperature = it }, valueRange = 0f..45f)
            HorizontalDivider()
            Text("Limiting factor: ${result.limitingFactor}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Illustrative relative rate index: ${"%.0f".format(result.relativeRatePercent)}%")
            Text(result.explanation)
            Text("Investigation challenge: increase light intensity while keeping carbon dioxide and temperature fixed. When does light stop being the limiting factor?", fontWeight = FontWeight.SemiBold)
            Text("Model limit: response curves and optimum temperature are simplified teaching assumptions. This percentage is not a measured photosynthesis rate and does not represent every plant species.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
