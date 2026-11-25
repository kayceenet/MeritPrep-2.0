package com.kaycee.meritprep

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ProjectileMotionAnimation(speed: Float, angleDegrees: Float) {
    val transition = rememberInfiniteTransition(label = "projectile-motion")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2600, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "projectile-progress"
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Projectile trajectory animation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                val theta = Math.toRadians(angleDegrees.toDouble())
                val u = speed.toDouble()
                val range = (u * u * sin(2.0 * theta) / 9.81).coerceAtLeast(0.01)
                val height = (u * u * sin(theta).let { it * it } / (2.0 * 9.81)).coerceAtLeast(0.01)
                val left = 14f
                val bottom = size.height - 12f
                fun point(t: Double): Offset {
                    val x = range * t
                    val y = 4.0 * height * t * (1.0 - t)
                    return Offset(left + (x / range).toFloat() * (size.width - 28f), bottom - (y / height).toFloat() * (size.height - 28f))
                }
                for (i in 0 until 60) {
                    drawLine(Color(0xFF1565C0), point(i / 60.0), point((i + 1) / 60.0), strokeWidth = 2.5f)
                }
                drawLine(Color.Gray, Offset(left, bottom), Offset(size.width - 8f, bottom), strokeWidth = 2f)
                drawCircle(Color(0xFFD84315), radius = 6f, center = point(progress.toDouble()))
            }
            Text("The moving marker follows the ideal parabolic path. Air resistance and wind are omitted.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun SatelliteOrbitAnimation() {
    val transition = rememberInfiniteTransition(label = "satellite-orbit")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(5000, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "satellite-angle"
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Satellite orbit animation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(170.dp)) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = size.height * 0.38f
                drawCircle(Color(0xFF90A4AE), radius, center, style = Stroke(width = 2f))
                drawCircle(Color(0xFF1565C0), radius * 0.24f, center)
                val radians = angle * PI / 180.0
                val satellite = Offset(center.x + radius * cos(radians).toFloat(), center.y + radius * sin(radians).toFloat())
                drawCircle(Color(0xFFFFA000), radius * 0.075f, satellite)
                drawLine(Color(0xFFFFA000), center, satellite, strokeWidth = 1f)
            }
            Text("Schematic circular orbit. The animation is not to scale and does not show orbital perturbations.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun ParticleKineticAnimation(temperatureKelvin: Float) {
    val transition = rememberInfiniteTransition(label = "particle-motion")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = (2f * PI).toFloat(),
        animationSpec = infiniteRepeatable(tween(1800, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "particle-phase"
    )
    val amplitude = (temperatureKelvin / 800f).coerceIn(0.05f, 1f) * 17f + 2f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Particle motion animation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(130.dp)) {
                for (i in 0 until 15) {
                    val column = i % 5
                    val row = i / 5
                    val baseX = size.width * (column + 1) / 6f
                    val baseY = size.height * (row + 1) / 4f
                    val dx = sin(phase + i * 1.7f) * amplitude
                    val dy = cos(phase * 1.2f + i * 0.9f) * amplitude
                    drawCircle(Color(0xFF00897B), radius = 5f, center = Offset(baseX + dx, baseY + dy))
                }
            }
            Text("Higher temperature increases the average kinetic energy of particles. The particle paths here are schematic, not a molecular-dynamics calculation.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
